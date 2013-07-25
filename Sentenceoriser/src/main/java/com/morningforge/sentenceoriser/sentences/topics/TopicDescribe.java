package com.morningforge.sentenceoriser.sentences.topics;

import android.content.Context;
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
    private WordArray characters;
    private WordArray topics;
    private WordArray statusYou;
    private WordArray statusMe;
    private WordArray statusUs;

    private String topic = "";
    private String character1 = "";
    private String character2 = "";
    private String statYou = "";
    private String statMe = "";
    private String statUs = "";

    private boolean character1Active = true;
    private boolean character2Active = true;
    private boolean statYouActive = true;
    private boolean statMeActive = true;
    private boolean statUsActive = true;

    private boolean character1Custom = false,
            character2Custom = false,
            topicCustom = false,
            statYouCustom = false,
            statMeCustom = false,
            statUsCustom = false;


    //Initiatise the WordArrays using specific files
    public TopicDescribe(Context context){
        characters = new WordArray(context, "DescribeCharacters");
        topics = new WordArray(context, "DescribeTopics");
        statusYou = new WordArray(context, "DescribeStatusYou");
        statusMe = new WordArray (context, "DescribeStatusMe");
        statusUs = new WordArray (context, "DescribeStatusUs");
    }

    //Generates a random number between min and max
    private int randomiser (int min, int max){
        Random rand = new Random();

        return (rand.nextInt(max) + min);
    }

    //Generate the topic
    public String generateTopic(){

        if (topicCustom == false){ topic = topics.getWord();}
        if (!character1Custom){character1 = characters.getWord();}
        if (!character2Custom){character2 = characters.getWord();}
        if (!statYouCustom){statYou = statusYou.getWord();}
        if (!statMeCustom){statMe = statusMe.getWord();}
        if (!statUsCustom){statUs = statusUs.getWord();}

        return describeToMeSentence();
    }

    public void setTopic(String customTopic){topic = customTopic;}
    public void setCharacter1(String customTopic){character1 = customTopic;}
    public void setCharacter2(String customTopic){character2 = customTopic;}
    public void setStatYou(String customTopic){statYou = customTopic;}
    public void setStatusMe(String customTopic){statMe = customTopic;}
    public void setStatUs(String customTopic){statUs = customTopic;}

    public void setCharacter1Active (boolean active){character1Active = active;}
    public void setCharacter2Active (boolean active){character2Active = active;}
    public void setStatYouActive (boolean active){statYouActive = active;}
    public void setStatMeActive (boolean active){statMeActive = active;}
    public void setStatUsActive (boolean active){statUsActive = active;}

    public void setCharacter1Custom (boolean enabled){character1Custom = enabled;}
    public void setCharacter2Custom (boolean enabled){character2Custom = enabled;}
    public void setStatYouCustom (boolean enabled){statYouCustom = enabled;}
    public void setStatMeCustom (boolean enabled){statMeCustom = enabled;}
    public void setStatUsCustom (boolean enabled){statUsCustom = enabled;}
    public void setTopicCustom (boolean enabled){topicCustom = enabled;}

    private String describeToMeSentence(){

        //Only use one modifier
        boolean mod = true;

        String sentence = "Explain to me " + topic;           //Topic

        if (character2Active){
            sentence = sentence + " like you're " + character2;    //Your character
        }

        if (randomiser(1, 2) == 1 && statYouActive){
            mod = false;
            sentence = sentence + " and you" + statYou;     //Your status
        }

        if ((randomiser(1, 2) == 1 || character1Custom) && character1Active){
            sentence = sentence + ", and I'm " + character1;  //My character
            if (randomiser(1, 2) == 1 && mod || statMeActive){
                mod = false;
                sentence = sentence + " " + statMe;         //My status
            }
        }

        if (randomiser(1, 5) >= 3 && mod && statUsActive){
            sentence = sentence + ", and we're " + statUs;  //Our status
        }
        sentence = sentence + ".";

        return sentence;
    }
}