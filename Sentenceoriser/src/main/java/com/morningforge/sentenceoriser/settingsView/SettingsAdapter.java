package com.morningforge.sentenceoriser.settingsView;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.morningforge.sentenceoriser.R;

import java.util.ArrayList;

/**
 * Created by Ben on 7/08/13.
 */
public class SettingsAdapter extends ArrayList {
    private Context context;
    private ArrayList<SettingsRow> Rows;

    public void addRow(String sentence, String preferencesID, String word){
        Rows.add(new SettingsRow(sentence, preferencesID, word));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final SettingsRow row = Rows.get(i);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.settings_row, null);
        }

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = settings.edit();

        TextView sentence = (TextView)view.findViewById(R.id.settingsTextStatic);
        final TextView word = (TextView)view.findViewById(R.id.settingsTextWord);
        EditText editWord = (EditText)view.findViewById(R.id.settingsEditWord);
        TableRow settingsRow = (TableRow)view.findViewById(R.id.settingsRowLayout);

        sentence.setText(row.getSentence());
        word.setText(row.getWord());

        settingsRow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String modeType = "ON";

                row.incMode();
                switch (row.getMode()){
                    case 0: word.setVisibility(View.VISIBLE);
                        modeType = "ON";
                        break;
                    case 1: word.setVisibility(View.GONE);
                        modeType = "CUSTOM";
                        break;
                    case 2: word.setVisibility(View.VISIBLE);
                        break;
                }

                preferencesEditor.putString(row.getPreferencesID(),modeType);
                preferencesEditor.commit();
            }
        });

        return view;
    }
}
