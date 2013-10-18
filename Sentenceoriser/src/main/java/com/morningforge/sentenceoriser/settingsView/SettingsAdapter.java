package com.morningforge.sentenceoriser.settingsView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final Context context = getContext();
        LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = infalInflater.inflate(R.layout.settings_row, null);
        }

        View viewLayout = infalInflater.inflate(R.layout.settings_layout, null);

        final SettingsRow row = rows.get(i);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = settings.edit();

        /*
        final View toastLayout = infalInflater.inflate(R.layout.custom_toast, null);
        final TextView toastText = (TextView) toastLayout.findViewById(R.id.textToShow);
        final Toast toast;
        toast = new Toast(context);
        toast.setDuration(5);
        if (toast.getView() != toastLayout){
            toast.setView(toastLayout);
        }
        */

        if (row != null){
            TextView sentence = (TextView)view.findViewById(R.id.settingsTextStatic);
            TextView sentence2 = (TextView)view.findViewById(R.id.settingsTextStatic2);
            final TextView word = (TextView)view.findViewById(R.id.settingsTextWord);
            final LinearLayout settingsRow = (LinearLayout)view.findViewById(R.id.settingsRowLayout);
            final EditText editWord = (EditText)view.findViewById(R.id.settingsEditWord);
            row.setEditWord(editWord);
            final View finalView = view;
            final String preferencesID = row.getPreferencesID();

            sentence.setText(row.getSentence());
            sentence2.setText(row.getSentence2());
            word.setText(row.getWord());

            String preferencesIDMode = settings.getString(preferencesID + "Mode", "");

            if (preferencesIDMode.equals("ON")){
                row.setMode(0);
            } else if (preferencesIDMode.equals("CUSTOM")){
                row.setMode(1);
            } else if (preferencesIDMode.equals("OFF")){
                row.setMode(2);
            }

            switch (row.getMode()) {
                case 0:
                    word.setVisibility(View.VISIBLE);
                    editWord.setVisibility(View.GONE);
                    settingsRow.setBackgroundColor(finalView.getResources().getColor(R.color.background));
                    break;
                case 1:
                    word.setVisibility(View.GONE);
                    editWord.setVisibility(View.VISIBLE);
                    editWord.setHint(row.getWord());
                    editWord.setText(settings.getString(preferencesID, ""));
                    break;
                case 2:
                    word.setVisibility(View.VISIBLE);
                    editWord.setVisibility(View.GONE);
                    settingsRow.setBackgroundColor(Color.GRAY);
                    break;
            }

            settingsRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (row.isChangeable()) {
                        row.incMode();
                        if (row.getMode() == 2 && !row.isCanDisable()) {
                            row.incMode();
                        }
                        switch (row.getMode()) {
                            case 0:
                                word.setVisibility(View.VISIBLE);
                                editWord.setVisibility(View.GONE);
                                settingsRow.setBackgroundColor(finalView.getResources().getColor(R.color.background));

                                preferencesEditor.putString(preferencesID + "Mode", "ON");
                                preferencesEditor.commit();
                                break;
                            case 1:
                                word.setVisibility(View.GONE);
                                editWord.setVisibility(View.VISIBLE);
                                editWord.setHint(row.getWord());
                                editWord.setText(settings.getString(preferencesID, ""));

                                preferencesEditor.putString(preferencesID + "Mode", "CUSTOM");
                                preferencesEditor.commit();
                                break;
                            case 2:
                                word.setVisibility(View.VISIBLE);
                                editWord.setVisibility(View.GONE);
                                settingsRow.setBackgroundColor(Color.GRAY);

                                preferencesEditor.putString(preferencesID + "Mode", "OFF");
                                preferencesEditor.commit();
                                break;
                        }
                    }

                    editWord.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            preferencesEditor.putString(preferencesID, editWord.getText().toString());
                            preferencesEditor.commit();
                        }
                    });
                }
            });
        }
        return view;
    }
}
