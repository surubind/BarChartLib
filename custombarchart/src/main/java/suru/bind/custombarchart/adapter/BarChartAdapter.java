package suru.bind.custombarchart.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suru.bind.custombarchart.R;
import suru.bind.custombarchart.R2;
import suru.bind.custombarchart.model.ChartContent;

public class BarChartAdapter extends RecyclerView.Adapter<BarChartAdapter.ChartViewHolder> {
    //private Animation animBlink;
    private Context context;
    private List<ChartContent> chartList;
    private int axisColor;
    private Typeface axisTypeface;
    private int axisTextSize;
    private OnChartItemClicked callback;


    public BarChartAdapter(List<ChartContent> chartList, Context context, int axisColor, Typeface axisTypeface, int axisTextSize, OnChartItemClicked callback) {
        this.chartList = chartList;
        this.context = context;
        this.axisColor = axisColor;
        this.axisTypeface = axisTypeface;
        this.axisTextSize = axisTextSize;
        this.callback = callback;
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
        ChartContent content = chartList.get(position);

        double percent = getPercent(content.getValue(), content.getTotal());
        long height = getHeight(percent, 500);

        holder.iv.setImageDrawable(content.getIcon());

        Log.e("ChartContentListAdapter", "per:" + percent + "%");
        if (!TextUtils.isEmpty(percent + "") && percent >= 100d) {
            holder.tv.setText("100" + "%");
        } else {
            if (!TextUtils.isEmpty(percent + "") && String.valueOf(percent).contains(".0")) {
                holder.tv.setText(String.valueOf(percent).replace(".0", "") + "%");
            } else {
                holder.tv.setText(percent + "%");
            }
        }

        /*if (!TextUtils.isEmpty(content.getTitle()) && content.getTitle().equalsIgnoreCase("bharatqr")) {
            holder.tv_xaxis.setText("BQR");
        } else {
            holder.tv_xaxis.setText(content.getTitle());
        }*/


        holder.tv_xaxis.setText(content.getTitle());
        if (getAxisColor() != 0) {
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
        DrawableCompat.setTint(drawable, content.getColor());
        holder.rl.setBackground(drawable);

        drawable = holder.iv.getDrawable();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, content.getColor());
        holder.iv.setImageDrawable(drawable);

        ViewCompat.setBackgroundTintList(
                holder.v,
                ColorStateList.valueOf(content.getColor()));

        Log.e("MYCHARTDATA", "height:" + ((int) height));
        Log.e("MYCHARTDATA", "type:" + content.getTitle());
        holder.v.getLayoutParams().height = ((int) height);
        holder.v.invalidate();
        holder.v.requestLayout();

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClicked(chartList.get(position));
                //setAnimation(holder.tv, position);
                //Toast.makeText(context, " " + content.getTitle() + " (" + (int) content.getValue() + "%) ", Toast.LENGTH_SHORT).show();
            }
        });
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClicked(chartList.get(position));
                //setAnimation(holder.tv, position);
                //Toast.makeText(context, " " + content.getTitle() + " (" + (int) content.getValue() + "%) ", Toast.LENGTH_SHORT).show();
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
        Log.d("AXISCOLOR", axisColor + "");
        return axisColor;
    }

    @Override
    public int getItemCount() {
        return chartList.size();
    }

    public class ChartViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.image1)
        AppCompatImageView iv;
        @BindView(R2.id.tv1)
        TextView tv;
        @BindView(R2.id.tv_xaxis)
        TextView tv_xaxis;
        @BindView(R2.id.rl1)
        RelativeLayout rl;
        @BindView(R2.id.view)
        View v;

        public ChartViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            /*iv = view.findViewById(R.id.image1);
            tv = view.findViewById(R.id.tv1);
            rl = view.findViewById(R.id.rl1);
            v = view.findViewById(R.id.view);
            tv_xaxis = view.findViewById(R.id.tv_xaxis);*/
        }
    }

    private long getHeight(double value, int i) {
        return Math.round((value / 100) * i);
    }

    private double getPercent(double value, double total) {
        String TAG = "populateChartList():";
        double p = (value / total) * 100;
        if (Double.isNaN(p)) {
            p = 0d;
        }
        String s = String.format("%.1f", p);
        double m = 0.0;
        try {
            m = Double.parseDouble(s);
        } catch (Exception e) {
            m = p;
        }
        //m = 100d;
        Log.e(TAG, "value:" + value + "/" + total + "=" + m);
        //return Math.round(p);
        return m;
    }

}