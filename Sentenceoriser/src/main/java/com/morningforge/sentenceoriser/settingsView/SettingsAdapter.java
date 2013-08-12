package com.morningforge.sentenceoriser.settingsView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
public class SettingsAdapter extends ArrayAdapter<SettingsRow> {
    private Context context;
    private ArrayList<SettingsRow> rows;

    public SettingsAdapter(Context context, int textViewResourceId, ArrayList<SettingsRow> rows) {
        super(context, textViewResourceId);
        this.rows = rows;

    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        context = getContext();
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.settings_row, null);
        }

        final SettingsRow row = rows.get(i);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = settings.edit();

        if (row != null){
            TextView sentence = (TextView)view.findViewById(R.id.settingsTextStatic);
            final TextView word = (TextView)view.findViewById(R.id.settingsTextWord);
            final EditText editWord = (EditText)view.findViewById(R.id.settingsEditWord);
            final LinearLayout settingsRow = (LinearLayout)view.findViewById(R.id.settingsRowLayout);

            sentence.setText(row.getSentence());
            word.setText(row.getWord());

            final View finalView = view;
            settingsRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String modeType = "ON";

                    row.incMode();
                    switch (row.getMode()){
                        case 0:
                            word.setVisibility(View.VISIBLE);
                            settingsRow.setBackgroundColor(finalView.getResources().getColor(R.color.background));
                            modeType = "ON";
                            break;
                        case 1:
                            word.setVisibility(View.GONE);
                            editWord.setVisibility(View.VISIBLE);
                            editWord.setHint(row.getWord());
                            modeType = "CUSTOM";
                            break;
                        case 2:
                            word.setVisibility(View.VISIBLE);
                            editWord.setVisibility(View.GONE);
                            settingsRow.setBackgroundColor(Color.GRAY);
                            modeType =  "OFF";
                            break;
                    }

                    preferencesEditor.putString(row.getPreferencesID(),modeType);
                    preferencesEditor.commit();
                }
            });
        }

        return view;
    }
}
