package suru.bind.barchartlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import suru.bind.barchartlib.adapter.BarChartAdapter;
import suru.bind.barchartlib.adapter.OnChartItemClicked;
import suru.bind.barchartlib.builder.BarChart;
import suru.bind.barchartlib.model.ChartContent;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recycler;
    private List<ChartContent> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);

        double total = 50;
        System.out.println(TAG + "Total:" + total);

        double[] values = {15, 25, 10};
        String[] type = {"Card", "UPI", "BQR"};

        Drawable[] images = {getDrawable(R.drawable.ic_card_pie), getDrawable(R.drawable.ic_upi_pie), getDrawable(R.drawable.ic_bharatqr_pie)};
        final int[] colors = {Color.parseColor("#FF0000"), Color.parseColor("#6200EE"),
                Color.parseColor("#03DAC5")};

        list.clear();

        for (int i = 0; i < images.length; i++) {
            list.add(new ChartContent(images[i], type[i], colors[i], values[i], total));
        }

        BarChartAdapter adapter = new BarChart.BarChartBuilder(MainActivity.this, list)
                .setXAxisColor(Color.YELLOW)
                .setXAxisTextSize(20)
                .addItemClickListener(new OnChartItemClicked() {
                    @Override
                    public void onItemClicked(ChartContent content) {
                        Toast.makeText(MainActivity.this, content.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                })
                .buildBarChartAdapter();

        Log.d(TAG, "Count:" + adapter.getItemCount() + "");
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(horizontalLayoutManager);
        recycler.setAdapter(adapter);

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

    private long getHeight(double value, int i) {
        return Math.round((value / 100) * i);
    }

    private void verify(String week) {
        String TAG = "VERIFY";
        long target = 10;
        long finalAmount = 150;
        long[] achieved = {0, 15, 1, 12};
        long[] targetAmount = {15, 25, 45, 55};


        int currentWeek = Integer.parseInt(week);

        ArrayList<Long> shifted = new ArrayList<>();

        int temp = currentWeek;
        boolean[] b1 = {false, false, false, false};
        int[] mmm = {0, 0, 0, 0};
        int countOfOne = 0;
        for (int i = 0; i < achieved.length; i++) {
            b1[i] = achieved[i] >= target;

            if (i < temp) {
                if (b1[i]) {
                    mmm[i] = 1;
                } else {
                    mmm[i] = 0;
                }
            } else {
                mmm[i] = 1;
            }
            if (mmm[i] == 1) {
                countOfOne += 1;
            }
            Log.d(TAG, "verify: mmm[" + i + "]: " + mmm[i]);
        }

        for (int i = 0; i < countOfOne; i++) {
            shifted.add(targetAmount[i]);
        }

        for (int i = 0; i < mmm.length; i++) {
            if (mmm[i] == 0) {
                shifted.add(i, 0L);
            }
        }

        for (int i = 0; i < shifted.size(); i++) {
            targetAmount[i] = shifted.get(i);
            Log.d(TAG, "verify: shifted[" + i + "]: " + shifted.get(i) + " targetAmount[" + i + "]: " + targetAmount[i]);
            //Log.d(TAG, "verify: targetAmount[" + i + "]: " + targetAmount[i]);
        }
    }
}