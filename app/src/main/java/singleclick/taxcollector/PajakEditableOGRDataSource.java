package singleclick.taxcollector;

import com.nutiteq.components.CullState;
import com.nutiteq.db.OGRFileHelper;
import com.nutiteq.editable.datasources.EditableOGRVectorDataSource;
import com.nutiteq.geometry.Geometry;
import com.nutiteq.projections.Projection;
import com.nutiteq.style.LineStyle;
import com.nutiteq.style.PointStyle;
import com.nutiteq.style.PolygonStyle;
import com.nutiteq.style.StyleSet;
import com.nutiteq.ui.Label;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Hafizh Herdi on 10/1/2014.
 */
public class PajakEditableOGRDataSource extends EditableOGRVectorDataSource{

    public PajakEditableOGRDataSource(Projection proj, String fileName, String tableName) throws IOException
    {
        super(proj,fileName,tableName);
    }

    public List<Geometry> getUserData()
    {
        return super.ogrHelper.loadData(ogrHelper.getDataExtent());
    }

    @Override
    protected Label createLabel(Map<String, String> userData) {
        return null;
    }

    @Override
    protected void notifyElementChanged(Geometry element) {
        super.notifyElementChanged(element);
    }

    @Override
    protected void notifyElementsChanged() {
        super.notifyElementsChanged();
    }

    @Override
    protected StyleSet<PointStyle> createPointStyleSet(Map<String, String> stringStringMap, int i) {
        return null;
    }

    @Override
    protected StyleSet<LineStyle> createLineStyleSet(Map<String, String> stringStringMap, int i) {
        return null;
    }

    @Override
    protected StyleSet<PolygonStyle> createPolygonStyleSet(Map<String, String> stringStringMap, int i) {
        return null;
    }
}
