package com.testfest.chartanalysis.acharts;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.testfest.chartanalysis.ChartData;
import com.testfest.chartanalysis.R;

public class AChartCartesianFragment extends Fragment {

    public AChartCartesianFragment() { }

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
        seriesRenderer.setXTitle(ChartData.CART_X_TITLE);
        seriesRenderer.setYTitle(ChartData.CART_Y_TITLE);
        seriesRenderer.setApplyBackgroundColor(true);
        seriesRenderer.setMarginsColor(Color.TRANSPARENT);
        seriesRenderer.setBackgroundColor(Color.TRANSPARENT);
        seriesRenderer.setMarginsColor(Color.WHITE);

        String seriesTitle = ChartData.SERIES1_MSG;
        XYSeries series1 = new XYSeries(seriesTitle);
        series1.add(1, 0.2);
        series1.add(5, 0.3);
        series1.add(8, 0.4);
        series1.add(15, 0.5);
        series1.add(19, 0.7);
        series1.add(21, .9);
        series1.add(24, 1.0);
        dataSet.addSeries(series1);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        seriesRenderer.addSeriesRenderer(renderer);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        String series2Title = ChartData.SERIES2_MSG;
        XYSeries series2 = new XYSeries(series2Title);
        series2.add(1, .77);
        series2.add(5, 0.8);
        series2.add(8, 0.85);
        series2.add(15, 0.87);
        series2.add(19, 0.9);
        series2.add(21, .94);
        series2.add(24, 1.0);
        dataSet.addSeries(series2);

        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer2.setColor(Color.RED);
        seriesRenderer.addSeriesRenderer(renderer2);
        renderer2.setPointStyle(PointStyle.CIRCLE);
        renderer2.setFillPoints(true);

        View view = inflater.inflate(R.layout.achart_engine, null);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);

        final GraphicalView chartView = ChartFactory.getLineChartView(getActivity(), dataSet, seriesRenderer);
        seriesRenderer.setSelectableBuffer(100);

        chartView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                SeriesSelection seriesSelection = chartView.getCurrentSeriesAndPoint();
                if(seriesSelection != null) {
                    if(seriesSelection.getValue() > 0.6) {
                        Toast.makeText(getActivity(), "Frustration is imminent", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "Go home while you can", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        seriesRenderer.setClickEnabled(true);
        seriesRenderer.setSelectableBuffer(10);

        layout.addView(chartView, new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT));

        return view;
    }
}
