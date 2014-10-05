package singleclick.taxcollector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nutiteq.components.Bounds;
import com.nutiteq.components.Color;
import com.nutiteq.components.CullState;
import com.nutiteq.components.Envelope;
import com.nutiteq.components.MapPos;
import com.nutiteq.components.Options;
import com.nutiteq.datasources.raster.MBTilesRasterDataSource;
import com.nutiteq.db.SpatialLiteDbHelper;
import com.nutiteq.editable.datasources.EditableOGRVectorDataSource;
import com.nutiteq.editable.layers.EditableGeometryLayer;
import com.nutiteq.geometry.Geometry;
import com.nutiteq.geometry.Line;
import com.nutiteq.geometry.Point;
import com.nutiteq.geometry.Polygon;
import com.nutiteq.layers.Layer;
import com.nutiteq.log.Log;
import com.nutiteq.projections.EPSG3857;
import com.nutiteq.rasterdatasources.HTTPRasterDataSource;
import com.nutiteq.rasterdatasources.RasterDataSource;
import com.nutiteq.rasterlayers.RasterLayer;
import com.nutiteq.style.LabelStyle;
import com.nutiteq.style.LineStyle;
import com.nutiteq.style.PointStyle;
import com.nutiteq.style.PolygonStyle;
import com.nutiteq.style.StyleSet;
import com.nutiteq.ui.DefaultLabel;
import com.nutiteq.ui.Label;
import com.nutiteq.utils.UnscaledBitmapLoader;
import com.nutiteq.vectorlayers.GeometryLayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import singleclick.taxcollector.utils.MapUtils;

/**
 *
 * Shows usage of EditableMapView with Spatialite database file or OGR shape file
 *
 *  Enables offline editing of points, lines and polygons. 
 *  Supports both Spatialite 3.x and 4.x formats.
 *
 *  See https://github.com/nutiteq/hellomap3d/wiki/Editable-MapView for details
 *
 * Layers:
 *  RasterLayer with TMS data source- base map
 *  EditableGeometryLayer - layer with editable Spatialite data source / OGR data source (depending on file extension)
 *
 * If Spatialite data source is detected, the Activity shows first list of tables in selected Spatialite database file,
 * and then opens for viewing and editing  selected one. It also creates toolbar for set of editing functions.
 *
 * @author mtehver
 *
 */
public class EditableVectorFileMapActivity extends EditableMapActivityBase{
    // about 2000 lines/polygons for high-end devices is fine, for older devices <1000
    // for points 5000 would work fine with almost any device
    private static final int MAX_ELEMENTS = 1000;

    // Style sets for elements
    private StyleSet<PointStyle> pointStyleSet;
    private StyleSet<LineStyle> lineStyleSet;
    private StyleSet<PolygonStyle> polygonStyleSet;
    private LabelStyle labelStyle;
    private RasterLayer mbLayer;
    private String dataNOP;
    private EditableGeometryLayer dbEditableLayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createBaseLayer() {
        setupMap();
        RasterDataSource dataSource = new HTTPRasterDataSource(new EPSG3857(), 0, 20, "http://otile1.mqcdn.com/tiles/1.0.0/osm/{zoom}/{x}/{y}.png");
        RasterLayer mapLayer = new RasterLayer(dataSource, 0);
        mapView.getLayers().setBaseLayer(mapLayer);

        try {
            MBTilesRasterDataSource mbdataSource = new MBTilesRasterDataSource(new EPSG3857(), 13, 19, Environment.getExternalStorageDirectory()+ File.separator+"pajak_data"+ File.separator+"imgpajak.mbtiles", false, this);
            mbLayer = new RasterLayer(mbdataSource, 123);
            mapView.getLayers().addLayer(mbLayer);
        } catch (IOException e) {
            Toast.makeText(this, "ERROR " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }

        mapView.setFocusPoint(mapView.getLayers().getBaseLayer().getProjection().fromWgs84(106.821902, -6.194537));
        mapView.setZoom(12f);
    }

    private void setupMap()
    {
        mapView.getOptions().setTextureMemoryCacheSize(20 * 1024 * 1024);
        mapView.getOptions().setCompressedMemoryCacheSize(8 * 1024 * 1024);
        mapView.getOptions().setPersistentCacheSize(100 * 1024 * 1024);

        mapView.getOptions().setBackgroundPlaneDrawMode(Options.DRAW_BITMAP);
        mapView.getOptions().setBackgroundPlaneBitmap(
                UnscaledBitmapLoader.decodeResource(getResources(),
                        R.drawable.background_plane));
        mapView.getOptions().setClearColor(Color.WHITE);
        mapView.getOptions().setMapListener(new PajakMapListener(EditableVectorFileMapActivity.this,mapView));
    }

    @Override
    protected void createEditableLayers() {
        // read filename from extras        
        createStyleSets();
        createEditableOGRLayers();

    }

    @Override
    protected List<EditableGeometryLayer> getEditableLayers() {
        List<EditableGeometryLayer> layers = new ArrayList<EditableGeometryLayer>();
        for (Layer layer : mapView.getComponents().layers.getLayers()) {
            if (layer instanceof EditableGeometryLayer) {
                layers.add((EditableGeometryLayer) layer);
            }
        }
        return layers;
    }

    @Override
    protected void createEditableElement() {
        AlertDialog.Builder typeBuilder = new AlertDialog.Builder(this);
        typeBuilder.setTitle("Choose type");
        final String[] items = new String[] { "Point", "Line", "Polygon" };
        typeBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Map<String, String> userData = new HashMap<String, String>();
                switch (item) {
                    case 0:
                        mapView.createElement(Point.class, userData);
                        break;
                    case 1:
                        mapView.createElement(Line.class, userData);
                        break;
                    case 2:
                        mapView.createElement(Polygon.class, userData);
                        break;
                }
            }
        });
        AlertDialog typeDialog = typeBuilder.create();
        typeDialog.show();
    }

    @Override
    protected void attachEditableElementToLayer(Geometry element) {
        List<EditableGeometryLayer> layers = getEditableLayers();
        if (layers.isEmpty()) {
            return;
        }
        if (element instanceof Point) {
            Point point = (Point) element;
            point.setStyleSet(pointStyleSet);
            layers.get(0).add(point);
        } else if (element instanceof Line) {
            Line line = (Line) element;
            line.setStyleSet(lineStyleSet);
            layers.get(0).add(line);
        } else if (element instanceof Polygon) {
            Polygon polygon = (Polygon) element;
            polygon.setStyleSet(polygonStyleSet);
            layers.get(0).add(polygon);
        }
    }

    @Override
    protected List<String> getEditableElementUserColumns(Geometry element) {
        @SuppressWarnings("unchecked")
        Map<String, String> userData = (Map<String, String>) element.userData;
        return new ArrayList<String>(userData.keySet());
    }

    private void createStyleSets() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float dpi = metrics.density;

        pointStyleSet = new StyleSet<PointStyle>();
        PointStyle pointStyle = PointStyle.builder().setColor(Color.GREEN).setSize(0.2f).build();
        pointStyleSet.setZoomStyle(0, pointStyle);

        lineStyleSet = new StyleSet<LineStyle>();
        LineStyle lineStyle = LineStyle.builder().setWidth(0.1f).setColor(Color.BLUE).build();
        lineStyleSet.setZoomStyle(0, lineStyle);

        polygonStyleSet = new StyleSet<PolygonStyle>();
        PolygonStyle polygonStyle = PolygonStyle.builder().setColor(Color.BLUE | Color.GREEN).build();
        polygonStyleSet.setZoomStyle(0, polygonStyle);

        labelStyle =
                LabelStyle.builder()
                        .setEdgePadding((int) (12 * dpi))
                        .setLinePadding((int) (6 * dpi))
                        .setTitleFont(Typeface.create("Arial", Typeface.BOLD), (int) (16 * dpi))
                        .setDescriptionFont(Typeface.create("Arial", Typeface.NORMAL), (int) (13 * dpi))
                        .build();
    }

    private void createEditableOGRLayers() {
        PajakEditableOGRDataSource dataSource;
        try {
            dataSource = new PajakEditableOGRDataSource(new EPSG3857(), Environment.getExternalStorageDirectory()+ File.separator+"pajak_data"+ File.separator+"3171041005_region.shp" , null) {

                @Override
                public Collection<Geometry> loadElements(CullState cullState) {
                    return super.loadElements(cullState);
                }

                @Override
                protected Label createLabel(Map<String, String> userData) {
                    return EditableVectorFileMapActivity.this.createLabel(userData);
                }

                @Override
                protected StyleSet<PointStyle> createPointStyleSet(Map<String, String> userData, int zoom) {
                    return pointStyleSet;
                }

                @Override
                protected StyleSet<LineStyle> createLineStyleSet(Map<String, String> userData, int zoom) {
                    return lineStyleSet;
                }

                @Override
                protected StyleSet<PolygonStyle> createPolygonStyleSet(Map<String, String> userData, int zoom) {
                    return polygonStyleSet;
                }

            };
        } catch (IOException e) {
            Log.error(e.getLocalizedMessage());
            Toast.makeText(this, "ERROR " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            return;
        }
        dataSource.setMaxElements(MAX_ELEMENTS);
        dbEditableLayer = new EditableGeometryLayer(dataSource);
        mapView.getLayers().addLayer(dbEditableLayer);

        // zoom map to data extent
        Envelope extent = dbEditableLayer.getDataExtent();
        mapView.setBoundingBox(new Bounds(extent.minX, extent.maxY, extent.maxX, extent.minY), false);

        System.out.println("get size editable "+dbEditableLayer.getAll().size());

        dataNOP = getIntent().getStringExtra("nop");
        System.out.println("data NOP "+dataNOP);
        if(dataNOP!=null)
        {
            searchInVector(dataNOP,dataSource);
        }

    }


    private void searchInVector(String nop,PajakEditableOGRDataSource dataSource)
    {

        System.out.println("MAPS layer found "+mapView.getLayers().getAllLayers().size());

        for(Geometry g : dataSource.getUserData())
        {
            System.out.println("guserdata "+ ((Map<String, String>)g.userData).get("D_NOP"));
            if(((Map<String, String>)g.userData).get("D_NOP").equals(nop))
            {

                ((Polygon)g).setStyleSet(MapUtils.getFoundPolygonStyle(this));
                System.out.println("MAPS get same NOP element " + ((Polygon) g).getVertexList().get(0));
                dataSource.notifyElementChanged(g);
                mapView.getLayers().removeLayer(dbEditableLayer);
                System.out.println("MAPS layer after remove "+mapView.getLayers().getAllLayers().size());
                mapView.getLayers().addLayer(new EditableGeometryLayer(dataSource));
                System.out.println("MAPS layer after add "+mapView.getLayers().getAllLayers().size());
                //dbEditableLayer.removeAll(dataSource.getUserData());
                //dbEditableLayer.addAll(dataSource.getUserData());
                mapView.setFocusPoint(((Polygon)g).getVertexList().get(3));

                mapView.setZoom(20);
            }
        }
        /*
        for(Layer layer : mapView.getLayers().getAllLayers())
        {

            if(layer instanceof EditableGeometryLayer)
            {
                System.out.println("MAPS editable layer size " + ((EditableGeometryLayer) layer).getAll().size());
                for(Geometry geo : ((EditableGeometryLayer) layer).getAll())
                {

                    Map<String, String> data = (Map<String, String>)geo.userData;

                    if(data.get("D_NOP").equals(nop))
                    {
                        System.out.println("MAPS get same NOP element");
                    }else
                    {
                        System.out.println("MAPS NOP "+data.get("D_NOP"));
                    }
                }
            }else if(layer instanceof GeometryLayer)
            {
                System.out.println("MAPS geometry layer size " + ((GeometryLayer) layer).getAll().size());
            }else if(layer instanceof RasterLayer)
            {
                System.out.println("MAPS raster layer found ");
            }else
            {
                System.out.println("MAPS unknown layer found " + layer.getClass().getName());
            }
        }*/
    }

    private Label createLabel(Map<String, String> userData) {
        StringBuffer labelTxt = new StringBuffer();
        for(Map.Entry<String, String> entry : userData.entrySet()){
            labelTxt.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        return new DefaultLabel("Data:", labelTxt.toString(), labelStyle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_get_coord :
                Toast.makeText(EditableVectorFileMapActivity.this, "coordinate "+mapView.getLayers().getBaseLayer().getProjection().toWgs84(mapView.getFocusPoint().x, mapView.getFocusPoint().y),Toast.LENGTH_LONG).show();
                MapPos wgs48pos = mapView.getLayers().getBaseLayer().getProjection().toWgs84(mapView.getFocusPoint().x,mapView.getFocusPoint().y);
                String coordinate = wgs48pos.y+" , "+wgs48pos.x;
                Intent intent = new Intent(this, MainActivity.class);
                //intent.putExtra("searchKey", dataNOP);
                intent.putExtra("searchKey", "317104100500100040");
                intent.putExtra("searchType", "NOP");
                intent.putExtra("objekUsahaType", "");
                intent.putExtra("dataCoord",coordinate);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
