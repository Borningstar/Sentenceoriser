package com.morningforge.sentenceoriser.settingsView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
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
    private ArrayList<SettingsRow> rows;

    public SettingsAdapter(Context context, int textViewResourceId, ArrayList<SettingsRow> rows) {
        super(context, textViewResourceId);
        this.rows = rows;

    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = getContext();
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.settings_row, null);
        }

        final SettingsRow row = rows.get(i);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = settings.edit();

        if (row != null){
            TextView sentence = (TextView)view.findViewById(R.id.settingsTextStatic);
            TextView sentence2 = (TextView)view.findViewById(R.id.settingsTextStatic2);
            final TextView word = (TextView)view.findViewById(R.id.settingsTextWord);
            final EditText editWord = (EditText)view.findViewById(R.id.settingsEditWord);
            final LinearLayout settingsRow = (LinearLayout)view.findViewById(R.id.settingsRowLayout);

            sentence.setText(row.getSentence());
            sentence2.setText(row.getSentence2());
            word.setText(row.getWord());

            final View finalView = view;
            settingsRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (row.isChangeable()){
                        row.incMode();
                        if (row.getMode() == 2 && !row.isCanDisable()){row.incMode();}
                        switch (row.getMode()){
                            case 0:
                                word.setVisibility(View.VISIBLE);
                                editWord.setVisibility(View.GONE);
                                settingsRow.setBackgroundColor(finalView.getResources().getColor(R.color.background));

                                preferencesEditor.putString(row.getPreferencesID() + "Mode", "ON");

                                break;
                            case 1:
                                word.setVisibility(View.GONE);
                                editWord.setVisibility(View.VISIBLE);
                                editWord.setHint(row.getWord());

                                preferencesEditor.putString(row.getPreferencesID() + "Mode", "CUSTOM");

                                break;
                            case 2:
                                word.setVisibility(View.VISIBLE);
                                editWord.setVisibility(View.GONE);
                                settingsRow.setBackgroundColor(Color.GRAY);

                                preferencesEditor.putString(row.getPreferencesID() + "Mode", "OFF");

                                break;
                        }

                        preferencesEditor.commit();
                    }
                }
            });

            editWord.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }


                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    preferencesEditor.putString(row.getPreferencesID(), editWord.getText().toString());
                    preferencesEditor.commit();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }

        return view;
    }
}
