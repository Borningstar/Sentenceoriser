package com.morningforge.sentenceoriser.sentences.topics;

import android.content.Context;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 1/9/13
 * Time: 5:50 PM
 * Generates and returns topics
 */
public class TopicGenerator {

    //Create various topic types
    private TopicTrapped topicTrapped;
    private TopicWins topicWins;
    private TopicDescribe topicDescribe;

    private String sentenceType = "Random";

    //Initiatise the topics. Context is eventually used by WordArray to access files
    public TopicGenerator(Context context){
        topicTrapped = new TopicTrapped(context);
        topicWins = new TopicWins(context);
        topicDescribe = new TopicDescribe(context);
    }

    //Generates a random number between min and max
    private int randomiser (int min, int max){
        // TODO: make actually generate values between min and max
        Random rand = new Random();

        return (rand.nextInt(max) + min);
    }

    //Returns the type of topic most recently generated
    public String getTopicType(){
        return sentenceType;
    }

    //Generates a random topic
    public String generateTopic(){

        String sentence = null;
        switch (randomiser(0, 3)){
            case 0: sentence = topicTrapped.generateTopic();
                break;
            case 1: sentence = topicWins.generateTopic();
                break;
            case 2: sentence = topicDescribe.generateTopic();
                break;
        }

        return sentence;
    }

    //Generates a specific topic
    public String generateTopic(int type){

        String sentence = null;

        if (type < 0){
            type = 0;
        }

        switch (type){
            case 0:
                sentenceType = "Random";
                switch (randomiser(0, 3)){
                    case 0: sentence = topicTrapped.generateTopic();
                        break;
                    case 1: sentence = topicWins.generateTopic();
                        break;
                    case 2: sentence = topicDescribe.generateTopic();
                }
                break;
            case 1: sentence = topicTrapped.generateTopic();
                sentenceType = "You're trapped...";
                break;
            case 2: sentence = topicWins.generateTopic();
                sentenceType = "Who Wins?";
                break;
            case 3: sentence = topicDescribe.generateTopic();
        }

        return sentence;
    }
}
