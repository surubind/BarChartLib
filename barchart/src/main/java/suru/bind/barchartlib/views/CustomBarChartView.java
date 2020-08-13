package suru.bind.barchartlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import suru.bind.barchartlib.R;
import suru.bind.barchartlib.adapter.BarChartAdapter;

public class CustomBarChartView extends LinearLayout {
    private RecyclerView barchart_recycler;
    private TextView titleView;
    private ConstraintLayout layout;

    private String title;
    private int titleColor;
    private int titleSize;
    private int chartHeight;
    private Typeface titleFont;
    private int chartBackground;
    private boolean canShowTitle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CustomBarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.custombarchartview, this, true);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            title = a.getString(R.styleable.CustomView_title);
            titleColor = a.getColor(R.styleable.CustomView_titleColor, Color.BLACK);
            titleSize = a.getInteger(R.styleable.CustomView_titleSize, 18);
            chartHeight = a.getInteger(R.styleable.CustomView_chartHeight, 100);
            titleFont = a.getFont(R.styleable.CustomView_titleFont);
            chartBackground = a.getColor(R.styleable.CustomView_chartBackgroundColor, Color.WHITE);
            canShowTitle = a.getBoolean(R.styleable.CustomView_canShowTitle, false);
        } finally {
            a.recycle();
        }

        // Throw an exception if required attributes are not set
        if (title == null) {
            title = "";
        }

        if (titleFont == null) {
            titleFont = ResourcesCompat.getFont(getContext(), R.font.montserrat_regular);
        }

        init(title, titleColor, titleSize, titleFont, chartBackground, chartHeight, canShowTitle);
    }

    // Setup views
    private void init(String title, int color, int size, Typeface font, int background, int chartSize, boolean showTitle) {
        titleView = findViewById(R.id.barchart_title);
        layout = findViewById(R.id.layout);
        barchart_recycler = findViewById(R.id.barchart_recycler);

        setTitle(title);
        setTitleColor(color);
        setTitleSize(size);
        setTitleFont(font);
        setChartBackground(background);
        setChartHeight(chartSize);
        canShowTitle(showTitle);

    }

    private void canShowTitle(boolean showTitle) {
        if (canShowTitle) {
            this.titleView.setVisibility(VISIBLE);
        } else {
            this.titleView.setVisibility(GONE);
        }
    }

    public void setAdapter(BarChartAdapter adapter) {
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
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

    public void setTitle(@Nullable String title) {
        this.titleView.setText(title);
    }

    public void setTitleColor(int color) {
        this.titleView.setTextColor(color);
    }

    public void setTitleFont(Typeface typeface) {
        this.titleView.setTypeface(typeface);

    }

    public void setTitleSize(int size) {
        this.titleView.setTextSize(size);
    }

    public void setChartHeight(int height) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (height * scale + 0.5f);
        this.barchart_recycler.getLayoutParams().height = pixels;
        this.barchart_recycler.invalidate();
        this.barchart_recycler.requestFocus();
    }

    public void setChartBackground(int color) {
        this.layout.setBackgroundColor(color);
    }
}