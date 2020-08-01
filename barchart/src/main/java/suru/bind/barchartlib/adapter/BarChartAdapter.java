package suru.bind.barchartlib.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import suru.bind.barchartlib.R;
import suru.bind.barchartlib.model.ChartContent;

public class BarChartAdapter extends RecyclerView.Adapter<BarChartAdapter.ChartViewHolder> {
    //private Animation animBlink;
    private Context context;
    private int lastPosition = -1;
    private List<ChartContent> chartList;
    private int axisColor;
    private Typeface axisTypeface;
    private int axisTextSize;

    public BarChartAdapter(List<ChartContent> chartList, Context context, int axisColor, Typeface axisTypeface, int axisTextSize) {
        this.chartList = chartList;
        this.context = context;
        this.axisColor = axisColor;
        this.axisTypeface = axisTypeface;
        this.axisTextSize = axisTextSize;
        /*animBlink = AnimationUtils.loadAnimation(context,
                R.anim.blink_info);*/
    }

    @Override
    public ChartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View chartView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bar_chart_item, parent, false);
        return new ChartViewHolder(chartView);
    }

    @Override
    public void onBindViewHolder(final ChartViewHolder holder, final int position) {
        holder.iv.setImageDrawable(chartList.get(position).getIcon());
        Log.e("ChartContentListAdapter", "per:" + chartList.get(position).getValue() + "%");
        if (!TextUtils.isEmpty(chartList.get(position).getValue() + "") && chartList.get(position).getValue() >= 100d) {
            holder.tv.setText("100" + "%");
        } else {
            if (!TextUtils.isEmpty(chartList.get(position).getValue() + "") && String.valueOf(chartList.get(position).getValue()).contains(".0")) {
                holder.tv.setText(String.valueOf(chartList.get(position).getValue()).replace(".0", "") + "%");
            } else {
                holder.tv.setText(chartList.get(position).getValue() + "%");
            }
        }

        /*if (!TextUtils.isEmpty(chartList.get(position).getTitle()) && chartList.get(position).getTitle().equalsIgnoreCase("bharatqr")) {
            holder.tv_xaxis.setText("BQR");
        } else {
            holder.tv_xaxis.setText(chartList.get(position).getTitle());
        }*/


        holder.tv_xaxis.setText(chartList.get(position).getTitle());
        if (getAxisColor() > 0) {
            holder.tv_xaxis.setTextColor(getAxisColor());
        }

        if (getTypeFace() != null) {
            holder.tv_xaxis.setTypeface(getTypeFace());
        }

        if (getAxisTextSize() > 0) {
            holder.tv_xaxis.setTextSize(axisTextSize);
        }

        //setAnimation(holder.tv, position);
        Drawable drawable = holder.rl.getBackground();
        drawable = DrawableCompat.wrap(drawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(drawable, chartList.get(position).getColor());
        holder.rl.setBackground(drawable);

        drawable = holder.iv.getDrawable();
        drawable = DrawableCompat.wrap(drawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(drawable, chartList.get(position).getColor());
        holder.iv.setImageDrawable(drawable);

        holder.v.setBackgroundColor(chartList.get(position).getColor());

        GradientDrawable chart_drawable = (GradientDrawable) holder.v.getBackground();
        chart_drawable.setColor(chartList.get(position).getColor());


        //holder.v.setMinimumHeight((int) horizontalGrocderyList.get(position).getHeight());
        Log.e("MYCHARTDATA", "height:" + ((int) chartList.get(position).getHeight()));
        Log.e("MYCHARTDATA", "type:" + chartList.get(position).getTitle());
        holder.v.getLayoutParams().height = ((int) chartList.get(position).getHeight());
        holder.v.invalidate();
        holder.v.requestLayout();

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setAnimation(holder.tv, position);
                //Toast.makeText(context, " " + chartList.get(position).getTitle() + " (" + (int) chartList.get(position).getValue() + "%) ", Toast.LENGTH_SHORT).show();
            }
        });
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setAnimation(holder.tv, position);
                //Toast.makeText(context, " " + chartList.get(position).getTitle() + " (" + (int) chartList.get(position).getValue() + "%) ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int getAxisTextSize() {
        return axisTextSize;
    }

    private Typeface getTypeFace() {
        return axisTypeface;
    }

    private int getAxisColor() {
        return axisColor;
    }

    @Override
    public int getItemCount() {
        return chartList.size();
    }

    public class ChartViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView iv;
        TextView tv, tv_xaxis;
        RelativeLayout rl;
        View v;

        public ChartViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.image1);
            tv = view.findViewById(R.id.tv1);
            rl = view.findViewById(R.id.rl1);
            v = view.findViewById(R.id.view);
            tv_xaxis = view.findViewById(R.id.tv_xaxis);
        }
    }

}