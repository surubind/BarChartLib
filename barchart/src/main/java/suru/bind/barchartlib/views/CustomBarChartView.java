package suru.bind.barchartlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import suru.bind.barchartlib.R;
import suru.bind.barchartlib.adapter.BarChartAdapter;

public class CustomBarChartView extends LinearLayout {
    private RecyclerView barchart_recycler;
    private TextView titleView;

    public CustomBarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.custombarchartview, this, true);

        String title;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomBarChartView, 0, 0);

        try {
            title = a.getString(R.styleable.CustomBarChartView_custom_bar_title);
        } finally {
            a.recycle();
        }

        // Throw an exception if required attributes are not set
        if (title == null) {
            title = "Custom Title";
        }


        init(title);
    }

    // Setup views
    private void init(String title) {
        titleView = findViewById(R.id.barchart_title);
        barchart_recycler = findViewById(R.id.barchart_recycler);
        titleView.setText(title);

    }

    public void setAdapter(Context context, BarChartAdapter adapter) {
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        this.barchart_recycler.setLayoutManager(horizontalLayoutManager);
        this.barchart_recycler.setAdapter(adapter);
        runLayoutAnimation(this.barchart_recycler);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_recycler);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        recyclerView.setFocusable(false);
    }

    public void setCustomTitle(@Nullable String title) {
        this.titleView.setText(title);
    }

    public void setCustomTitleColor(int color) {
        this.titleView.setTextColor(color);
    }

    public void setCustomTitleTheme(Typeface typeface) {
        this.titleView.setTypeface(typeface);

    }

    public void setCustomTitleSize(int size) {
        this.titleView.setTextSize(size);
    }
}