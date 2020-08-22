package suru.bind.barchartlib;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suru.bind.custombarchart.adapter.BarChartAdapter;
import suru.bind.custombarchart.adapter.OnChartItemClicked;
import suru.bind.custombarchart.builder.BarChart;
import suru.bind.custombarchart.model.ChartContent;
import suru.bind.custombarchart.views.CustomBarChartView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<ChartContent> list = new ArrayList<>();

    @BindView(R.id.barChartView)
    CustomBarChartView barChartView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChart();
            }
        });

        setChart();
    }

    private void setChart() {
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
    }

}