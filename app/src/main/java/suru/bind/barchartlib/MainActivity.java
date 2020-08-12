package suru.bind.barchartlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import suru.bind.barchartlib.adapter.BarChartAdapter;
import suru.bind.barchartlib.adapter.OnChartItemClicked;
import suru.bind.barchartlib.builder.BarChart;
import suru.bind.barchartlib.model.ChartContent;
import suru.bind.barchartlib.views.CustomBarChartView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recycler;
    private List<ChartContent> list = new ArrayList<>();
    private CustomBarChartView barChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        barChartView = findViewById(R.id.barChartView);

        double total = 50;
        System.out.println(TAG + "Total:" + total);

        double[] values = {15, 25, 10};
        String[] type = {"Card", "UPI", "BQR"};

        Drawable[] images = {getDrawable(R.drawable.ic_card_pie), getDrawable(R.drawable.ic_upi_pie), getDrawable(R.drawable.ic_bharatqr_pie)};
        final int[] colors = {Color.parseColor("#FF0000"), Color.parseColor("#6200EE"),
                Color.parseColor("#03DAC5")};

        list.clear();

        for (int i = 0; i < values.length; i++) {
            list.add(new ChartContent(images[i], type[i], colors[i], values[i], total));
        }


        BarChartAdapter adapter = new BarChart.BarChartBuilder(MainActivity.this, list)
                .setXAxisColor(Color.BLACK)
                .setXAxisTextSize(10)
                .addItemClickListener(new OnChartItemClicked() {
                    @Override
                    public void onItemClicked(ChartContent content) {
                        Toast.makeText(MainActivity.this, content.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                })
                .buildBarChartAdapter();

        barChartView.setAdapter(adapter);
        //barChartView.setCustomTitle("Custom Chart");
        //barChartView.setCustomTitleColor(Color.BLUE);
        barChartView.setCustomTitleSize(16);
        //barChartView.setCustomTitleFont(typeface);
        barChartView.setChartHeight(250);
        barChartView.showTitle(true);

    }

}