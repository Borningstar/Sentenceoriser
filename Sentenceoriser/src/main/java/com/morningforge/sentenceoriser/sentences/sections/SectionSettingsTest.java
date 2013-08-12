package com.morningforge.sentenceoriser.sentences.sections;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.settingsView.SettingsAdapter;
import com.morningforge.sentenceoriser.settingsView.SettingsRow;

import java.util.ArrayList;

/**
 * Created by Ben on 7/08/13.
 */
public class SectionSettingsTest extends ListFragment {

    /** Called when the activity is first created. */
    private ArrayList<SettingsRow> rows = null;
    private SettingsAdapter adapter;
    private Context c;
    private Runnable viewRows;

    public SectionSettingsTest(Context c){
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.settings_layout, container, false);

        rows = new ArrayList<SettingsRow>();
        adapter = new SettingsAdapter(c, R.layout.settings_row, rows);
        setListAdapter(adapter);

/*        viewRows = new Runnable() {
            @Override
            public void run() {
                createRows();
            }
        };*/

        createRows();

        return rootView;
    }

    private Runnable returnRes = new Runnable(){

        @Override
        public void run() {
            if (rows != null && rows.size() > 0){
                adapter.notifyDataSetChanged();
                for (int i = 0; i < rows.size(); i++){
                    adapter.add(rows.get(i));
                }
            }
            adapter.notifyDataSetChanged();
        }
    };

    private void createRows(){

        SettingsRow row;
    //    row = new SettingsRow("Test", "arena_mode", "Test");
    //    rows.add(row);
     //   row = new SettingsRow("Test 2", "situation_mode","Test woot");
     //   rows.add(row);

        if (rows != null && rows.size() > 0){
            adapter.notifyDataSetChanged();
            for (int i = 0; i < rows.size(); i++){
                adapter.add(rows.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

}