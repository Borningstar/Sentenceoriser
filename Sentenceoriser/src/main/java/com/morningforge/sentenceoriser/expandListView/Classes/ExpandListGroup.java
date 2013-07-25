package com.morningforge.sentenceoriser.expandListView.Classes;

import java.util.ArrayList;

/**
 * Created by Ben on 23/07/13.
 */
public class ExpandListGroup {
    private String sentence;
    private String modeSettings;
    private ArrayList<ExpandListChild> Items;

    public ExpandListGroup(String sentence, String modeSettings){
        this.sentence = sentence;
        this.modeSettings = modeSettings;
    }

    public String getModeSettings() {
        return modeSettings;
    }
    public void setModeSettings(String modeSettings) {
        this.modeSettings = modeSettings;
    }
    public String getSentence() {
        return sentence;
    }
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public ArrayList<ExpandListChild> getItems() {
        return Items;
    }
    public void setItems(ArrayList<ExpandListChild> Items) {
        this.Items = Items;
    }
}


