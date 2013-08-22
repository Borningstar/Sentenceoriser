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

    //Initiatise the WordArrays using specific files
    public TopicWins(Context context){
        subjects = new WordArray(context, "WinsSubjects");
        modifiers = new WordArray(context, "WinsModifiers");
        objects = new WordArray(context, "WinsObjects");

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

        return whoWinsSentence();
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

        String ad1ModifierMode = settings.getString("ad1ModifierMode", "ON");
        String ad1Modifier = settings.getString("ad1Modifier", "Modifier");
        String ad1Mode = settings.getString("ad1Mode", "ON");
        String ad1 = settings.getString("ad1", "Adversary");
        String ad1WeaponMode = settings.getString("ad1WeaponMode", "ON");
        String ad1Weapon = settings.getString("ad1Weapon", "Weapon");

        String ad2ModifierMode = settings.getString("ad2ModifierMode", "ON");
        String ad2Modifier = settings.getString("ad2Modifier", "Modifier");
        String ad2Mode = settings.getString("ad2Mode", "ON");
        String ad2 = settings.getString("ad2", "Adversary");
        String ad2WeaponMode = settings.getString("ad2WeaponMode", "ON");
        String ad2Weapon = settings.getString("ad2Weapon", "Weapon");

        if (!ad1ModifierMode.equals("CUSTOM")){ad1Modifier = modifiers.getWord();}
        if (!ad1WeaponMode.equals("CUSTOM")){ad1Weapon = objects.getWord();}
        if (!ad1Mode.equals("CUSTOM")){ad1 = subjects.getWord();}
        if (!ad2ModifierMode.equals("CUSTOM")){ad2Modifier = modifiers.getWord();}
        if (!ad2WeaponMode.equals("CUSTOM")){ad2Weapon = objects.getWord();}
        if (!ad2Mode.equals("CUSTOM")){ad2 = subjects.getWord();}

        String sentence = "If ";                    //First part of the sentence

        //Construct first half of sentence
        //50% chance of a modifier being used
        if ((randomiser(1, 2) == 1) && (!ad1ModifierMode.equals("OFF"))){
            sentence = sentence + ad1Modifier + " ";
        }
        //Set participant
        sentence = sentence + ad1;
        //50% chance of a weapon being used
        if ((randomiser(1, 2) == 1) && (!ad1WeaponMode.equals("OFF"))){
            sentence = sentence + " used " + ad1Weapon + " to fight ";
        } else {
            sentence = sentence + " fought ";
        }

        //Construct second half of sentence
        //Set modifier
        if ((randomiser(1, 2) == 1) && (!ad2ModifierMode.equals("OFF"))){
            sentence = sentence + ad2Modifier + " ";
        }
        //Set participant
        sentence = sentence + ad2;
        //Set weapon
        if ((randomiser(1, 2) == 1) && (!ad2WeaponMode.equals("OFF"))){
            sentence = sentence + ", who's armed with " + ad2Weapon;
        }

        sentence = sentence + "... \r\n \r\n ... Then who would win?";

        return sentence;
    }
}
