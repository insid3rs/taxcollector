package singleclick.taxcollector.utils;

import android.app.Activity;

import com.nutiteq.components.Color;
import com.nutiteq.style.LineStyle;
import com.nutiteq.style.PolygonStyle;
import com.nutiteq.style.StyleSet;

/**
 * Created by Hafizh Herdi on 10/1/2014.
 */
public class MapUtils {

    private static int minZoom = 0;

    private MapUtils()
    {

    }

    public static StyleSet<PolygonStyle> getFoundPolygonStyle(Activity act)
    {
        StyleSet<PolygonStyle> blockStyle;
        blockStyle = new StyleSet<PolygonStyle>();
        PolygonStyle polygonStyle = PolygonStyle.builder().setColor(Color.BLUE& 0x1AFFFFFF).build();
        blockStyle.setZoomStyle(minZoom, polygonStyle);

        return blockStyle;
    }
}
