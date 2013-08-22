package com.morningforge.sentenceoriser.expandListView.Classes;

/**
 * Created by Ben on 23/07/13.
 */
public class ExpandListChild {
    private String sentence;
    private String modeSettings;
    private String[] modeTypes = {"ON", "CUSTOM", "OFF"};
    private int modeIndex = 0;

    public ExpandListChild(String sentence, String modeSettings){
        this.sentence = sentence;
        this.modeSettings = modeSettings;
    }

    public String getModeSettings(){
        return modeSettings;
    }

    public String getMode() {
        return modeTypes[modeIndex];
    }

    public void setMode(int mode) {
        modeIndex = mode;
    }

    public void incMode(){
        if (modeIndex < 2){
            modeIndex++;
        } else {
            modeIndex = 0;
        }
    }

    public String getSentence() {
        return sentence;
    }
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
