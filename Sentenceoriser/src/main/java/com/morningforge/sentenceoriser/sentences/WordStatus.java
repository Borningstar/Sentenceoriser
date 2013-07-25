package com.morningforge.sentenceoriser.sentences;

/**
 * Created by Ben on 8/07/13.
 */
public class WordStatus {
    private String name;
    private boolean custom = false;
    private boolean active = true;

    public WordStatus(String nameIn, boolean customIn, boolean activeIn){
        name = nameIn;
        custom = customIn;
        active = activeIn;
    }

    public WordStatus(){}

    public final void setAll(String nameIn, boolean customIn, boolean activeIn){
        name = nameIn;
        active = activeIn;
        custom = customIn;
    }

    public final void setName(String input){name = input;}
    public final void setCustom(boolean input){custom = input;}
    public final void setActive(boolean input){active = input;}

    public final String getName(){return name;}
    public final boolean getCustom(){return custom;}
    public final boolean getActive(){return active;}
}
