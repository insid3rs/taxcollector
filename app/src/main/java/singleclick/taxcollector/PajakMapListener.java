package singleclick.taxcollector;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.nutiteq.MapView;
import com.nutiteq.components.MapPos;
import com.nutiteq.geometry.Polygon;
import com.nutiteq.geometry.VectorElement;
import com.nutiteq.ui.MapListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Hafizh Herdi on 9/17/2014.
 */
public class PajakMapListener extends MapListener {

    private static final String TAG = PajakMapListener.class.getSimpleName();
    private double[][] polyCoords;
    private Activity activity;
    private MapView mapView;

    public PajakMapListener(Activity act,MapView mView)
    {
        activity = act;
        mapView = mView;
    }

    @Override
    public void onMapMoved() {

    }

    @Override
    public void onMapClicked(double v, double v2, boolean b) {
        Log.v(TAG, "listener map clicked");
    }

    @Override
    public void onVectorElementClicked(VectorElement vectorElement, double x, double y, boolean longClick) {
        Log.v(TAG,"listener element clicked");
        if(vectorElement instanceof Polygon){
            Polygon p = (Polygon) vectorElement;
            List<MapPos> vl = p.getVertexList();
            for(MapPos v : vl){
                Log.v(TAG, "vertex mapPos = " + v);
            }
            computeAreaWithGeographicLib(vl);
        }
    }

    private void computeAreaWithGeographicLib(List<MapPos> vl) {
        double area = 0.0;
        for (int i = 0; i < vl.size() - 1; i++) {
            MapPos p1 = mapView.getLayers().getBaseProjection()
                    .toWgs84(vl.get(i).x, vl.get(i).y);
            MapPos p2 = mapView.getLayers().getBaseProjection()
                    .toWgs84(vl.get(i + 1).x, vl.get(i + 1).y);
            area += Math.toRadians(p2.y - p1.y)
                    * (2 + Math.sin(Math.toRadians(p1.x)) + Math
                    .sin(Math.toRadians(p2.x)));

        }
        area = Math.abs(area * 6371009.0 * 6371009.0 / 2.0);

        Toast.makeText(activity,"Polygon area : "+String.format( "%.2f", area )+"meter2",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLabelClicked(VectorElement vectorElement, boolean b) {
        Log.v(TAG,"listener label clicked");
    }
}
