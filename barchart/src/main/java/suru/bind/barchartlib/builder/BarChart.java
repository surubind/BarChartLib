package suru.bind.barchartlib.builder;

import android.content.Context;
import android.graphics.Typeface;

import java.util.List;

import suru.bind.barchartlib.adapter.BarChartAdapter;
import suru.bind.barchartlib.adapter.OnChartItemClicked;
import suru.bind.barchartlib.model.ChartContent;

public class BarChart {
    public List<ChartContent> getChartList() {
        return chartList;
    }

    public Context getContext() {
        return context;
    }

    public int getAxisColor() {
        return axisColor;
    }

    public Typeface getAxisTypeface() {
        return axisTypeface;
    }

    public int getAxisTextSize() {
        return axisTextSize;
    }

    public OnChartItemClicked getCallback() {
        return callback;
    }

    private List<ChartContent> chartList;
    private Context context;
    private int axisColor;
    private Typeface axisTypeface;
    private int axisTextSize;
    private OnChartItemClicked callback;

    public BarChart(BarChartBuilder builder) {
        this.chartList = builder.chartList;
        this.context = builder.context;
        this.axisColor = builder.axisColor;
        this.axisTypeface = builder.axisTypeface;
        this.axisTextSize = builder.axisTextSize;
        this.callback = builder.callback;
        this.axisTextSize = builder.axisTextSize;
    }


    public static class BarChartBuilder {

        private List<ChartContent> chartList;
        private Context context;
        private int axisColor;
        private Typeface axisTypeface;
        private int axisTextSize;
        private OnChartItemClicked callback;

        public BarChartBuilder(Context context, List<ChartContent> chartList) {
            this.context = context;
            this.chartList = chartList;
        }

        public BarChartBuilder setXAxisColor(int axisColor) {
            this.axisColor = axisColor;
            return this;
        }

        public BarChartBuilder setXAxisTextSize(int axisTextSize) {
            this.axisTextSize = axisTextSize;
            return this;
        }

        public BarChartBuilder setXAxisTextTypeface(Typeface axisTypeface) {
            this.axisTypeface = axisTypeface;
            return this;
        }


        /*public BarChart buildBarChart() {
            return new BarChart(this);
        }*/

        public BarChartBuilder addItemClickListener(OnChartItemClicked callback) {
            this.callback = callback;
            return this;
        }

        public BarChartAdapter buildBarChartAdapter() {
            return new BarChartAdapter(this.chartList, this.context, this.axisColor, this.axisTypeface, this.axisTextSize, this.callback);
        }


    }

}
