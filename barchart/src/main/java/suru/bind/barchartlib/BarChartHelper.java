package suru.bind.barchartlib;

import android.content.Context;
import android.graphics.Typeface;

import java.util.List;

import suru.bind.barchartlib.adapter.BarChartAdapter;
import suru.bind.barchartlib.model.ChartContent;

public class BarChartHelper {
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ChartContent> getChartList() {
        return chartList;
    }

    public void setChartList(List<ChartContent> chartList) {
        this.chartList = chartList;
    }

    public int getAxisColor() {
        return axisColor;
    }

    public void setAxisColor(int axisColor) {
        this.axisColor = axisColor;
    }

    public Typeface getAxisTypeface() {
        return axisTypeface;
    }

    public void setAxisTypeface(Typeface axisTypeface) {
        this.axisTypeface = axisTypeface;
    }

    public int getAxisTextSize() {
        return axisTextSize;
    }

    public void setAxisTextSize(int axisTextSize) {
        this.axisTextSize = axisTextSize;
    }

    private Context context;
    private List<ChartContent> chartList;
    private int axisColor;
    private Typeface axisTypeface;
    private int axisTextSize;


    public BarChartAdapter getAdapter() {
        return new BarChartAdapter(chartList, context, axisColor, axisTypeface, axisTextSize);
    }
}
