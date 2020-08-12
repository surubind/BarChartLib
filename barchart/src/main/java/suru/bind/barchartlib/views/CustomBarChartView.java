package suru.bind.barchartlib.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import suru.bind.barchartlib.R;
import suru.bind.barchartlib.adapter.BarChartAdapter;

public class CustomBarChartView extends LinearLayout {
    private RecyclerView barchart_recycler;
    private TextView titleView;
    private LinearLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CustomBarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.custombarchartview, this, true);

        String title;
        int color;
        int size;
        Typeface font;
        int background;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            title = a.getString(R.styleable.CustomView_customTitle);
            color = a.getColor(R.styleable.CustomView_customTitleColor, Color.BLACK);
            size = a.getInteger(R.styleable.CustomView_customTitleSize, 18);
            font = a.getFont(R.styleable.CustomView_customTitleFont);
            background = a.getColor(R.styleable.CustomView_chartBackgroundColor, Color.WHITE);
        } finally {
            a.recycle();
        }

        // Throw an exception if required attributes are not set
        if (title == null) {
            title = "";
        }

        if (font == null) {
            font = ResourcesCompat.getFont(getContext(), R.font.montserrat_regular);
        }

        init(title, color, size, font, background);
    }

    // Setup views
    private void init(String title, int color, int size, Typeface font, int background) {
        titleView = findViewById(R.id.barchart_title);
        layout = findViewById(R.id.layout);
        barchart_recycler = findViewById(R.id.barchart_recycler);
        titleView.setText(title);
        titleView.setTextColor(color);
        titleView.setTextSize(size);
        titleView.setTypeface(font);
        layout.setBackgroundColor(background);

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

    public void setCustomTitle(@Nullable String title) {
        this.titleView.setText(title);
    }

    public void setCustomTitleColor(int color) {
        this.titleView.setTextColor(color);
    }

    public void setCustomTitleFont(Typeface typeface) {
        this.titleView.setTypeface(typeface);

    }

    public void setCustomTitleSize(int size) {
        this.titleView.setTextSize(size);
    }

    public void setChartHeight(int height) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (height * scale + 0.5f);
        this.barchart_recycler.getLayoutParams().height = pixels;
        this.barchart_recycler.invalidate();
        this.barchart_recycler.requestFocus();
    }

    public void showTitle(boolean b) {
        if (b) {
            this.titleView.setVisibility(VISIBLE);
        } else {
            this.titleView.setVisibility(GONE);
        }
    }
}