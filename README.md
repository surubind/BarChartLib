# BarChartLib

implementation 'com.github.surubind:BarChartLib:1.0.4'

 BarChartAdapter adapter = new BarChart.BarChartBuilder(MainActivity.this, list)
                .addItemClickListener(new OnChartItemClicked() {
                    @Override
                    public void onItemClicked(ChartContent content) {
                        Toast.makeText(MainActivity.this, content.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setXAxisTextSize(20)
                .setXAxisColor(Color.BLUE)
                .buildBarChartAdapter();

