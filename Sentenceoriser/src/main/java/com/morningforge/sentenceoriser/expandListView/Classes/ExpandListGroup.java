package com.morningforge.sentenceoriser.expandListView.Classes;

import java.util.ArrayList;

/**
 * Created by Ben on 23/07/13.
 */
public class ExpandListGroup {
    private String sentence;
    private String status;
    private ArrayList<ExpandListChild> Items;

    public String getSentence() {
        return sentence;
    }
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public ArrayList<ExpandListChild> getItems() {
        return Items;
    }
    public void setItems(ArrayList<ExpandListChild> Items) {
        this.Items = Items;
    }
}
