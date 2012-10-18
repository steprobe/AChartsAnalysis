package com.testfest.chartanalysis.acharts;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.testfest.chartanalysis.ChartData;
import com.testfest.chartanalysis.R;

public class AChartBarFragment extends Fragment {

    public AChartBarFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();
        XYMultipleSeriesRenderer seriesRenderer = new XYMultipleSeriesRenderer();

        seriesRenderer.setChartTitleTextSize(40);
        seriesRenderer.setChartTitle(ChartData.CHART_TITLE);
        seriesRenderer.setLabelsTextSize(20);
        seriesRenderer.setLegendTextSize(30);
        seriesRenderer.setStartAngle(23);
        seriesRenderer.setMargins(new int[] { 50, 50, 50, 50 });
        seriesRenderer.setZoomButtonsVisible(false);
        seriesRenderer.setPanEnabled(false);
        seriesRenderer.setPointSize(3);
        seriesRenderer.setAxisTitleTextSize(30);
        seriesRenderer.setXTitle("Hours in ISIS");
        seriesRenderer.setYTitle("Probability of Frustration");
        seriesRenderer.setApplyBackgroundColor(true);
        seriesRenderer.setMarginsColor(Color.TRANSPARENT);
        seriesRenderer.setBackgroundColor(Color.TRANSPARENT);
        seriesRenderer.setMarginsColor(Color.WHITE);
        seriesRenderer.setXAxisMax(1.0);

        String seriesTitle = "Series 1";
        XYSeries series1 = new XYSeries(seriesTitle);
        series1.add(1, 0.2);
        series1.add(2, 1.0);
        dataSet.addSeries(series1);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        seriesRenderer.addSeriesRenderer(renderer);

        View view = inflater.inflate(R.layout.achart_engine, null);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);

        final GraphicalView chartView = ChartFactory.getBarChartView(
                getActivity(), getBarDemoDataset(), getBarRenderer(), Type.DEFAULT);

        seriesRenderer.setClickEnabled(true);
        seriesRenderer.setSelectableBuffer(10);

        layout.addView(chartView, new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT));

        return view;
    }

    private XYMultipleSeriesRenderer getBarRenderer() {

        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPanEnabled(false);
        renderer.setZoomEnabled(false);
        renderer.setMarginsColor(Color.TRANSPARENT);
        renderer.setBackgroundColor(Color.TRANSPARENT);
        renderer.setMarginsColor(Color.WHITE);

        renderer.setMargins(new int[] {20, 30, 15, 20});
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(ChartData.SERIES1_COLOUR);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ChartData.SERIES1_COLOUR_GRAD_START);
        r.setGradientStop(200, ChartData.SERIES1_COLOUR);
        renderer.addSeriesRenderer(r);

        r = new SimpleSeriesRenderer();
        r.setColor(ChartData.SERIES3_COLOUR);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ChartData.SERIES3_COLOUR_GRAD_START);
        r.setGradientStop(200, ChartData.SERIES3_COLOUR);
        renderer.addSeriesRenderer(r);
        return renderer;
    }

    private XYMultipleSeriesDataset getBarDemoDataset() {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        final int nr = 5;
        Random r = new Random();
        for (int i = 0; i < 2; i++) {
          CategorySeries series = new CategorySeries("Demo series " + (i + 1));
          for (int k = 0; k < nr; k++) {
            series.add(100 + r.nextInt() % 100);
          }
          dataset.addSeries(series.toXYSeries());
        }
        return dataset;
      }
}
