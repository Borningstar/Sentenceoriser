package com.morningforge.sentenceoriser.settingsView;

/**
 * Created by Ben on 23/07/13.
 */
public class SettingsRow {
    private String sentence;
    private String word;
    private String preferencesID;        //Links the row to the preferences ID value
    private int mode = 0; //ON, CUSTOM, OFF

    public SettingsRow(String sentence, String preferencesID, String word){
        this.word = word;
        this.sentence = sentence;
        this.preferencesID = preferencesID;
    };

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPreferencesID(){
        return preferencesID;
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


}
