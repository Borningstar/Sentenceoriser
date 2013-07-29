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
public class TopicWins {

    //WordArrays to store words
    private WordArray subjects;
    private WordArray modifiers;
    private WordArray objects;

    //Initiatise the WordArrays using specific files
    public TopicWins(Context context){
        subjects = new WordArray(context, "WinsSubjects");
        modifiers = new WordArray(context, "WinsModifiers");
        objects = new WordArray(context, "WinsObjects");
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

    //Generate the topic with a custom subject
    public String generateTopic(String customSubject){

        String sentence =  whoWinsSentence(customSubject);

        return sentence;
    }

    // Generate a who wins topic
    private String whoWinsSentence (){

        String sentence = "If ";                    //First part of the sentence

        String p1 = subjects.getWord();             //First adversary
        String p1Modifier = modifiers.getWord();    //Modifier for the adversary, tall, strong, crazy, etc
        String p1Weapon = objects.getWord();        //Weapon for the adversary, gun, banana, etc

        String p2 = subjects.getWord();
        String p2Modifier = modifiers.getWord();
        String p2Weapon = objects.getWord();

        //Construct first half of sentence
        //50% chance of a modifier being used
        if (randomiser(1, 2) == 1){
            sentence = sentence + p1Modifier + " ";
        }
        //Set participant
        sentence = sentence + p1;
        //50% chance of a weapon being used
        if (randomiser(1, 2) == 1){
            sentence = sentence + " used " + p1Weapon + " to fight ";
        } else {
            sentence = sentence + " fought ";
        }

        //Construct second half of sentence
        //Set modifier
        if (randomiser(1, 2) == 1){
            sentence = sentence + p2Modifier + " ";
        }
        //Set participant
        sentence = sentence + p2;
        //Set weapon
        if (randomiser(1, 2) == 1){
            sentence = sentence + ", who's armed with " + p2Weapon;
        }

        sentence = sentence + "... \r\n \r\n ... Then who would win?";

        return sentence;
    }

    private String whoWinsSentence (String customSubject){

        String sentence = "If ";

        String p1 = customSubject;
        String p1Modifier = modifiers.getWord();
        String p1Weapon = objects.getWord();

        String p2 = subjects.getWord();
        String p2Modifier = modifiers.getWord();
        String p2Weapon = objects.getWord();

        //Construct first half of sentence
        //Set modifier
        if (randomiser(1, 2) == 1){
            sentence = sentence + p1Modifier + " ";
        }
        //Set participant
        sentence = sentence + p1;
        //Set weapon
        if (randomiser(1, 2) == 1){
            sentence = sentence + " used " + p1Weapon + " to fight ";
        } else {
            sentence = sentence + " fought ";
        }

        //Construct second half of sentence
        //Set modifier
        if (randomiser(1, 2) == 1){
            sentence = sentence + p2Modifier + " ";
        }
        //Set participant
        sentence = sentence + p2;
        //Set weapon
        if (randomiser(1, 2) == 1){
            sentence = sentence + ", who's armed with " + p2Weapon;
        }

        sentence = sentence + "... \r\n \r\n ... Then who would win?";

        return sentence;
    }

}
