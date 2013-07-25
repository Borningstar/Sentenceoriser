package com.morningforge.sentenceoriser.sentences.topics;

import android.content.Context;
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

    public WordStatus arena = new WordStatus(), equipment1 = new WordStatus(), equipment2 = new WordStatus(),
            equipment3 = new WordStatus(), situation = new WordStatus(), companion = new WordStatus();

    private int numEquip;

    public TopicTrapped(Context context){
        arenaArray = new WordArray(context, "TrappedArenas");
        equipmentArray = new WordArray(context, "TrappedEquipment");
        situationArray = new WordArray(context, "TrappedSituation");
        companionArray = new WordArray(context, "TrappedCompanions");
    }

    private int randomiser (int min, int max){
        Random rand = new Random();

        return (rand.nextInt(max) + min);
    }

    public String generateTopic(){

        if (arena.getCustom() == false){arena.setName(arenaArray.getWord());}
        if (equipment1.getCustom() == false){equipment1.setName(equipmentArray.getWord());}
        if (equipment2.getCustom() == false){equipment2.setName(equipmentArray.getWord());}
        if (equipment3.getCustom() == false){equipment3.setName(equipmentArray.getWord());}
        if (situation.getCustom() == false){situation.setName(situationArray.getWord());}
        if (companion.getCustom() == false){companion.setName(companionArray.getWord());}

        return whatDoYouDoGame();
    }

    private String whatDoYouDoGame (){

        String sentence = ("You're trapped " +  arena.getName());
        if (situation.getActive()){
            sentence += (", which is "+ situation.getName());
            if (randomiser(0, 2) == 1){
                sentence += "";
            }
        }
        if (companion.getActive()){
            sentence += (" with " + companion.getName());
            if (randomiser(0, 2) == 1){
                sentence += "";
            }
        }
        if (equipment1.getActive()){
        sentence += ( ".\r\n\n You're equipped with " +equipment1.getName());
        }
        sentence += (", " + equipment2.getName());
        if (randomiser(0, 2) == 1){
            sentence += "";
        }
        sentence += (" and " + equipment3.getName() + ". \r\n\n ...What do you do?");


        return sentence;
    }
}
