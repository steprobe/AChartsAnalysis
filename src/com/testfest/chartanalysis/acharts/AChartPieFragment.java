package com.testfest.chartanalysis.acharts;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chartdemo.demo.R;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

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

public class AChartPieFragment extends Fragment {

    public AChartPieFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        CategorySeries series = new CategorySeries("");
        DefaultRenderer seriesRenderer = new DefaultRenderer();

        seriesRenderer.setApplyBackgroundColor(true);
        seriesRenderer.setChartTitleTextSize(40);
        seriesRenderer.setChartTitle(ChartData.CHART_TITLE);
        seriesRenderer.setLabelsTextSize(20);
        seriesRenderer.setLegendTextSize(30);
        seriesRenderer.setStartAngle(23);
        seriesRenderer.setZoomEnabled(false);
        seriesRenderer.setMargins(new int[] { 50, 50, 50, 50 });
        seriesRenderer.setZoomButtonsVisible(false);
        seriesRenderer.setPanEnabled(false);

        series.add(ChartData.SERIES1_MSG, ChartData.SERIES1_VALUE);
        SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
        renderer.setColor(ChartData.SERIES1_COLOUR);
        seriesRenderer.addSeriesRenderer(renderer);

        series.add(ChartData.SERIES2_MSG, ChartData.SERIES2_VALUE);
        SimpleSeriesRenderer renderer2 = new SimpleSeriesRenderer();
        renderer2.setColor(ChartData.SERIES2_COLOUR);
        seriesRenderer.addSeriesRenderer(renderer2);

        series.add(ChartData.SERIES3_MSG, ChartData.SERIES3_VALUE);
        SimpleSeriesRenderer renderer3 = new SimpleSeriesRenderer();
        renderer3.setColor(ChartData.SERIES3_COLOUR);
        seriesRenderer.addSeriesRenderer(renderer3);

        View view = inflater.inflate(R.layout.achart_engine, null);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);
        final GraphicalView chartView = ChartFactory.getPieChartView(getActivity(), series, seriesRenderer);
        seriesRenderer.setClickEnabled(true);
        seriesRenderer.setSelectableBuffer(10);

        layout.addView(chartView, new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT));

        chartView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                SeriesSelection seriesSelection = chartView.getCurrentSeriesAndPoint();
                if(seriesSelection != null) {

                    if(seriesSelection.getValue() == ChartData.SERIES1_VALUE) {
                        Toast.makeText(getActivity(), ChartData.SERIES1_TOAST,
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(seriesSelection.getValue() == ChartData.SERIES2_VALUE) {
                        Toast.makeText(getActivity(), ChartData.SERIES2_TOAST
                                ,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), ChartData.SERIES3_TOAST,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}
