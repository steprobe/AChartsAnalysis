/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.testfest.chartanalysis;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChartLibraryListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String [] charts = getResources().getStringArray(R.array.chart_libs);

        setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, charts));
  }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        switch(position) {
            case 0:
                Intent acharts = new Intent(this, AChartEngineCharts.class);
                startActivity(acharts);
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "This bullshit just crashes", Toast.LENGTH_LONG).show();
                break;
        }
    }
}