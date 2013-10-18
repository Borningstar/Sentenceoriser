package com.morningforge.sentenceoriser.settingsView;

import android.widget.EditText;
import com.morningforge.sentenceoriser.R;

/**
 * Created by Ben on 23/07/13.
 */
public class SettingsRow {
    private String sentence;
    private String word;
    private String preferencesID;        //Links the row to the preferences ID value
    private String sentence2;
    private int mode = 0; //ON, CUSTOM, OFF
    private boolean changeable;
    private boolean canDisable;
    private EditText editWord;

    public SettingsRow(String sentence, String word, String sentence2, String preferencesID, boolean changeable, boolean canCustomise){
        this.word = word;
        this.sentence = sentence;
        this.sentence2 = sentence2;
        this.preferencesID = preferencesID;
        this.changeable = changeable;
        this.canDisable = canCustomise;
    }

    public String getWord() {
        return word;
    }

    public void setEditWord (EditText editText){
        this.editWord = editWord;
    }

    public EditText getEditWord(){
        return editWord;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPreferencesID(){
        return preferencesID;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void incMode(){
        if (mode < 2){
            mode++;
        } else {
            mode = 0;
        }
    }

    public String getSentence() {
        return sentence;
    }
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public boolean isCanDisable() {
        return canDisable;
    }

    public String getSentence2() {
        return sentence2;
    }
}
