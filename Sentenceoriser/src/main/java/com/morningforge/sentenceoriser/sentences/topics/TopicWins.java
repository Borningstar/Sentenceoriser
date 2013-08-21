package com.morningforge.sentenceoriser.sentences.topics;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.morningforge.sentenceoriser.sentences.WordArray;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 1/9/13
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TopicWins {

    //WordArrays to store words
    private WordArray subjects;
    private WordArray modifiers;
    private WordArray objects;
    Context context;

    private String ad1Weapon,
            ad2Modifier, ad2Weapon, ad2;


    private Boolean ad1WeaponActive,
            ad2ModifierActive, ad2WeaponActive;

    private Boolean ad1WeaponCustom, ad1Custom,
            ad2ModifierCustom, ad2WeaponCustom, ad2Custom;

    //Initiatise the WordArrays using specific files
    public TopicWins(Context context){
        subjects = new WordArray(context, "WinsSubjects");
        modifiers = new WordArray(context, "WinsModifiers");
        objects = new WordArray(context, "WinsObjects");

        ad1WeaponActive = true;

        ad2ModifierActive = true;
        ad2WeaponActive = true;
        ad2ModifierActive = true;

        ad1WeaponCustom = false;
        ad1Custom = false;

        ad2ModifierCustom = false;
        ad2WeaponCustom = false;
        ad2ModifierCustom = false;
        ad2Custom = false;

        this.context = context;
    }

    //Generates a random number between min and max
    private int randomiser (int min, int max){
        // TODO: make actually generate values between min and max
        Random rand = new Random();

        return (rand.nextInt(max) + min);
    }

    //Generate the topic
    public String generateTopic(){

        String sentence = whoWinsSentence();

        return sentence;
    }

    // Generate a who wins topic
    private String whoWinsSentence (){

        /*
        If Modifier
        Adversary
        used Weapon to fight
        fought Modifier
        Adversary
        who's armed with Weapon
        then who would win?
         */

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        String ad1ModifierMode = settings.getString("ad1ModifierMode", "ad1ModifierMode");
        String ad1Modifier = settings.getString("ad1Modifier", "ad1Modifier");

        String ad1Mode = settings.getString("ad1Mode", "ad1Mode");
        String ad1 = settings.getString("ad1", "ad1");

        if (ad1ModifierMode != "CUSTOM"){ad1Modifier = modifiers.getWord();}
        if (!ad1WeaponCustom){ad1Weapon = objects.getWord();}
        if (ad1Mode != "CUSTOM"){ad1 = subjects.getWord();}
        if (!ad2ModifierCustom){ad2Modifier = modifiers.getWord();}
        if (!ad2WeaponCustom){ad2Weapon = objects.getWord();}
        if (!ad2Custom){ad2 = subjects.getWord();}

        String sentence = "If ";                    //First part of the sentence

        //Construct first half of sentence
        //50% chance of a modifier being used
        if ((randomiser(1, 2) == 1) && (ad1ModifierMode != "OFF")){
            sentence = sentence + ad1Modifier + " ";
        }
        //Set participant
        sentence = sentence + ad1;
        //50% chance of a weapon being used
        if ((randomiser(1, 2) == 1) && ad1WeaponActive){
            sentence = sentence + " used " + ad1Weapon + " to fight ";
        } else {
            sentence = sentence + " fought ";
        }

        //Construct second half of sentence
        //Set modifier
        if ((randomiser(1, 2) == 1) && ad2ModifierActive){
            sentence = sentence + ad2Modifier + " ";
        }
        //Set participant
        sentence = sentence + ad2;
        //Set weapon
        if ((randomiser(1, 2) == 1) && ad2WeaponActive){
            sentence = sentence + ", who's armed with " + ad2Weapon;
        }

        sentence = sentence + "... \r\n \r\n ... Then who would win?";

        return sentence;
    }

    public void setAd1Weapon(String ad1Weapon) {
        this.ad1Weapon = ad1Weapon;
    }

    public void setAd2Modifier(String ad2Modifier) {
        this.ad2Modifier = ad2Modifier;
    }

    public void setAd2Weapon(String ad2Weapon) {
        this.ad2Weapon = ad2Weapon;
    }

    public void setAd2(String ad2) {
        this.ad2 = ad2;
    }

    public void setAd1WeaponActive(Boolean ad1WeaponActive) {
        this.ad1WeaponActive = ad1WeaponActive;
    }

    public void setAd2ModifierActive(Boolean ad2ModifierActive) {
        this.ad2ModifierActive = ad2ModifierActive;
    }

    public void setAd2WeaponActive(Boolean ad2WeaponActive) {
        this.ad2WeaponActive = ad2WeaponActive;
    }

    public void setAd1WeaponCustom(Boolean ad1WeaponCustom) {
        this.ad1WeaponCustom = ad1WeaponCustom;
    }

    public void setAd1Custom(Boolean ad1Custom) {
        this.ad1Custom = ad1Custom;
    }

    public void setAd2ModifierCustom(Boolean ad2ModifierCustom) {
        this.ad2ModifierCustom = ad2ModifierCustom;
    }

    public void setAd2WeaponCustom(Boolean ad2WeaponCustom) {
        this.ad2WeaponCustom = ad2WeaponCustom;
    }

    public void setAd2Custom(Boolean ad2Custom) {
        this.ad2Custom = ad2Custom;
    }
}
