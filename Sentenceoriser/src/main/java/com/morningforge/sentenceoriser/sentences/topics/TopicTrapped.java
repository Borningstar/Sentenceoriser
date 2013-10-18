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

    private int numEquip;

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
        You're trapped ARENA
        which is SITUATION
        with COMPANION
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

        int EquipNum = 0;

        String[] equipArray = new String[3];

        if (!arenaMode.equals("CUSTOM")){arena = arenaArray.getWord();}
        if (!situationMode.equals("CUSTOM")){situation = situationArray.getWord();}
        if (!companionMode.equals("CUSTOM")){companion = companionArray.getWord();}
        if (!equipment1Mode.equals("CUSTOM")){
            equipment1 = equipmentArray.getWord();
            equipArray[EquipNum] = equipment1;
            EquipNum++;
        }
        if (!equipment2Mode.equals("CUSTOM")){
            equipment2 = equipmentArray.getWord();
            equipArray[EquipNum] = equipment1;
            EquipNum++;
        }
        if (!equipment3Mode.equals("CUSTOM")){
            equipment3 = equipmentArray.getWord();
            equipArray[EquipNum] = equipment1;
            EquipNum++;
        }

        //Set arena
        String sentence = ("You're trapped " +  arena);

        //Set situation
        if ((randomiser(1, 2) == 1) && (!situationMode.equals("OFF"))){
            sentence += ", which is " + situation;
        }

        //Companion
        if ((randomiser(1, 2) == 1) && (!companionMode.equals("OFF"))){
            sentence += " with " + companion;
        }

        //Equipment 1
        if ((randomiser(1, 2) == 1) && (!equipment1Mode.equals("OFF"))){
            sentence += ".\r\n\n You're equipped with " + equipment1;
        }

        //Equipment 2
        if ((randomiser(1, 2) == 1) && (!equipment2Mode.equals("OFF")) && (numEquip == 1)){
            sentence += ", " + equipment2;
        }

        //Equipment 3
        if ((randomiser(1, 2) == 1) && (!equipment3Mode.equals("OFF")) && (numEquip == 2)){
            sentence += " and " + equipment3;
        }

        sentence += ". \r\n\n ... What do you do?";

        return sentence;
    }
}
