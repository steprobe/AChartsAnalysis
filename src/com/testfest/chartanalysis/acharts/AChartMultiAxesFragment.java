package com.testfest.chartanalysis.acharts;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.testfest.chartanalysis.ChartData;
import com.testfest.chartanalysis.R;

public class AChartMultiAxesFragment extends Fragment {

    public AChartMultiAxesFragment() { }

    protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
        List<double[]> yValues) {
      XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
      addXYSeries(dataset, titles, xValues, yValues, 0);
      return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
        List<double[]> yValues, int scale) {
      int length = titles.length;
      for (int i = 0; i < length; i++) {
        XYSeries series = new XYSeries(titles[i], scale);
        double[] xV = xValues.get(i);
        double[] yV = yValues.get(i);
        int seriesLength = xV.length;
        for (int k = 0; k < seriesLength; k++) {
          series.add(xV[k], yV[k]);
        }
        dataset.addSeries(series);
      }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();
        XYMultipleSeriesRenderer chartRenderer = new XYMultipleSeriesRenderer(2);

        chartRenderer.setChartTitleTextSize(40);
        chartRenderer.setChartTitle(ChartData.CHART_TITLE);
        chartRenderer.setLabelsTextSize(20);
        chartRenderer.setLegendTextSize(30);
        chartRenderer.setStartAngle(23);
        chartRenderer.setMargins(new int[] { 50, 50, 50, 50 });
        chartRenderer.setZoomButtonsVisible(false);
        chartRenderer.setPanEnabled(false);
        chartRenderer.setPointSize(3);
        chartRenderer.setAxisTitleTextSize(30);
        chartRenderer.setXTitle("Multi Chart Test");
        chartRenderer.setApplyBackgroundColor(true);
        chartRenderer.setMarginsColor(Color.TRANSPARENT);
        chartRenderer.setBackgroundColor(Color.TRANSPARENT);
        chartRenderer.setMarginsColor(Color.WHITE);

        String seriesTitle = "Series1";
        XYSeries series1 = new XYSeries(seriesTitle, 0);
        series1.add(1, 0.2);
        series1.add(5, 0.3);
        series1.add(8, 0.4);
        series1.add(15, 0.5);
        series1.add(19, 0.7);
        series1.add(21, .9);
        series1.add(24, 1.0);
        dataSet.addSeries(series1);

        XYSeriesRenderer series1renderer = new XYSeriesRenderer();
        chartRenderer.addSeriesRenderer(series1renderer);
        series1renderer.setPointStyle(PointStyle.CIRCLE);
        series1renderer.setFillPoints(true);

        String series2Title = "Series2";
        XYSeries series2 = new XYSeries(series2Title, 1);
        series2.add(1, 35);
        series2.add(5, 78);
        series2.add(8, 88);
        series2.add(15, 92);
        series2.add(19, 94);
        series2.add(21, 95);
        series2.add(24, 100);
        dataSet.addSeries(series2);

        XYSeriesRenderer series2renderer = new XYSeriesRenderer();
        series2renderer.setColor(Color.RED);
        chartRenderer.addSeriesRenderer(series2renderer);
        series2renderer.setPointStyle(PointStyle.CIRCLE);
        series2renderer.setFillPoints(true);

        //Align the different Y axes'
        chartRenderer.setYTitle("Series1", 0);
        chartRenderer.setYAxisAlign(Align.RIGHT, 0);
        chartRenderer.setYTitle("Series2", 1);
        chartRenderer.setYAxisAlign(Align.LEFT, 1);

        View view = inflater.inflate(R.layout.achart_engine, null);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);

        final GraphicalView chartView = ChartFactory.getLineChartView(getActivity(), dataSet, chartRenderer);
        chartRenderer.setSelectableBuffer(100);

        layout.addView(chartView, new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT));

        return view;
    }
}
