package com.morningforge.sentenceoriser.sentences.sections;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TableLayout;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.expandListView.Adapter.ExpandListAdapter;
import com.morningforge.sentenceoriser.expandListView.Classes.ExpandListChild;
import com.morningforge.sentenceoriser.expandListView.Classes.ExpandListGroup;
import com.morningforge.sentenceoriser.settingsView.SettingsAdapter;

import java.util.ArrayList;

/**
 * Created by Ben on 7/08/13.
 */
public class SectionSettingsTest extends Fragment {

    /** Called when the activity is first created. */
    private TableLayout table;
    private Context c;

    public SectionSettingsTest(Context c){
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.settings_row, container, false);



        return rootView;
    }

}