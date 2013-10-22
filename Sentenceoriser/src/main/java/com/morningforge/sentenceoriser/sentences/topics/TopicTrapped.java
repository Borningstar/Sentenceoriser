package com.morningforge.sentenceoriser.sentences.topics;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.morningforge.sentenceoriser.sentences.WordArray;
import com.morningforge.sentenceoriser.sentences.WordStatus;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 1/9/13
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TopicTrapped {

    private WordArray arenaArray;
    private WordArray equipmentArray;
    private WordArray situationArray;
    private WordArray companionArray;
    Context context;

    public TopicTrapped(Context context){
        arenaArray = new WordArray(context, "TrappedArenas");
        equipmentArray = new WordArray(context, "TrappedEquipment");
        situationArray = new WordArray(context, "TrappedSituation");
        companionArray = new WordArray(context, "TrappedCompanions");

        this.context = context;
    }

    private int randomiser (int min, int max){
        // TODO: make actually generate values between min and max
        Random rand = new Random();

        return (rand.nextInt(max) + min);
    }

    public String generateTopic(){

        return whatDoYouDoGame();
    }

    private String whatDoYouDoGame (){

        /*
        You're trapped with COMPANION
        in ARENA
        which is SITUATION
        You're equipped with EQUIPMENT1
        , EQUIPMENT2
        and EQUIPMENT3
        ... What do you do?
         */

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        String arena = settings.getString("arena", "Arena"),
                arenaMode = settings.getString("arenaMode", "ON"),
                situation = settings.getString("situation", "Situation"),
                situationMode = settings.getString("situationMode", "ON"),
                companion = settings.getString("companion", "Companion"),
                companionMode = settings.getString("companionMode", "ON"),
                equipment1 = settings.getString("equipment1", "Equipment"),
                equipment1Mode = settings.getString("equipment1Mode", "ON"),
                equipment2 = settings.getString("equipment2", "Equipment"),
                equipment2Mode = settings.getString("equipment2Mode", "ON"),
                equipment3 = settings.getString("equipment3", "Equipment"),
                equipment3Mode = settings.getString("equipment3Mode", "ON");

        int equipNum = 0;
        String[] selectedEquipArray = new String[3];

        if (arenaMode.equals("ON")){arena = arenaArray.getWord();}
        if (situationMode.equals("ON")){situation = situationArray.getWord();}
        if (companionMode.equals("ON")){companion = companionArray.getWord();}

        if (equipment1Mode.equals("ON")){
            selectedEquipArray[equipNum] = equipmentArray.getWord();
            equipNum++;
        } else if (equipment1Mode.equals("CUSTOM")){
            selectedEquipArray[equipNum] = equipment1;
            equipNum++;
        }
        if (equipment2Mode.equals("ON")){
            selectedEquipArray[equipNum] = equipmentArray.getWord();
            equipNum++;
        } else if (equipment2Mode.equals("CUSTOM")){
            selectedEquipArray[equipNum] = equipment2;
            equipNum++;
        }
        if (equipment3Mode.equals("ON")){
            selectedEquipArray[equipNum] = equipmentArray.getWord();
            equipNum++;
        } else if (equipment3Mode.equals("CUSTOM")){
            selectedEquipArray[equipNum] = equipment3;
            equipNum++;
        }

        String sentence = ("You're trapped");

        //Companion
        if ((randomiser(1, 2) == 1) && (!companionMode.equals("OFF"))){
            sentence += " with " + companion;
        }

        sentence += " " + arena;

        //Set situation
        if ((randomiser(1, 2) == 1) && (!situationMode.equals("OFF"))){
            sentence += " which is " + situation;
        }

        String equipment = "";

        //Equipment 1
        if (equipNum >= 1){
            equipment = ".\r\n\n You're equipped with " + selectedEquipArray[0];
        }

        //Equipment 2
        if ((randomiser(1, 2) == 1) && (equipNum >= 2)){
            equipment = ".\r\n\n You're equipped with " + selectedEquipArray[0] + " and " + selectedEquipArray[1];
        }

        //Equipment 3
        if ((randomiser(1, 2) == 1) && (equipNum == 3)){
            equipment = ".\r\n\n You're equipped with " + selectedEquipArray[0] + ", " + selectedEquipArray[1] + " and " + selectedEquipArray[2];
        }

        sentence += equipment + ". \r\n\n ... What do you do?";

        return sentence;
    }
}
