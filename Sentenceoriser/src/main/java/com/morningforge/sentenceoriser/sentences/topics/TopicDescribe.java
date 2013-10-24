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
public class TopicDescribe {

    //WordArrays to store words
    private WordArray characterArray;
    private WordArray topicArray;
    private WordArray statusYouArray;
    private WordArray statusMeArray;
    private WordArray statusUsArray;
    private Context context;



    //Initiatise the WordArrays using specific files
    public TopicDescribe(Context context){
        characterArray = new WordArray(context, "DescribeCharacters");
        topicArray = new WordArray(context, "DescribeTopics");
        statusYouArray = new WordArray(context, "DescribeStatusYou");
        statusMeArray = new WordArray (context, "DescribeStatusMe");
        statusUsArray = new WordArray (context, "DescribeStatusUs");
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

        return describeToMeSentence();
    }

    private String describeToMeSentence(){


        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        String topic = settings.getString("topic", "Topic"),
                topicMode = settings.getString("topicMode", "ON"),
                character1 = settings.getString("character1", "Character 1"),
                character1Mode = settings.getString("character1Mode", "ON"),
                character2 = settings.getString("character2", "Character 2"),
                character2Mode = settings.getString("character2Mode", "ON"),
                statYou = settings.getString("statYou", "Stat You"),
                statYouMode = settings.getString("statYouMode", "ON"),
                statMe = settings.getString("statMeMode", "Stat Me"),
                statMeMode = settings.getString("statMeMode", "ON"),
                statUs = settings.getString("statUs", "Stat Us"),
                statUsMode = settings.getString("statUsMode", "ON");

        if (topicMode.equals("ON")){topic = topicArray.getWord();}
        if (character1Mode.equals("ON")){character1 = characterArray.getWord();}
        if (character2Mode.equals("ON")){character2 = characterArray.getWord();}
        if (statYouMode.equals("ON")){statYou = statusYouArray.getWord();}
        if (statMeMode.equals("ON")){statMe = statusMeArray.getWord();}
        if (statUsMode.equals("ON")){statUs = statusUsArray.getWord();}

        /*
        Explain to me TOPIC
        like you're CHARACTER
        and you STATUS
        and I'm CHARACTER
        and I'm STATUS
        and we're STATUS.
        */

        //Only use one modifier
        boolean mod = true;

        String sentence = "Explain to me " + topic;           //Topic

        if (!character2Mode.equals("OFF")){
            sentence = sentence + " like you're " + character2;    //Your character
        }

        if (randomiser(1, 2) == 1 && (!statYouMode.equals("OFF"))){
            mod = false;
            sentence = sentence + " and you" + statYou;     //Your status
        }

        if ((randomiser(1, 2) == 1 && (!character1Mode.equals("OFF")))){
            sentence = sentence + ", and I'm " + character1;  //My character
            if (randomiser(1, 2) == 1 && mod && (!statMeMode.equals("OFF"))){
                mod = false;
                sentence = sentence + " " + statMe;         //My status
            }
        }

        if (randomiser(1, 5) >= 3 && mod && (!statUsMode.equals("OFF"))){
            sentence = sentence + ", and we're " + statUs;  //Our status
        }
        sentence = sentence + ".";

        return sentence;
    }
}