package com.morningforge.sentenceoriser.sentences;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ben on 17/05/13.
 */
public class WordArray {

    //Array to store the a list of words
    private ArrayList<String> wordArray = new ArrayList<String>();
    private int[] lastWords = new int[3];
    private int lastWordsCounter = 0;

    //Constructor
    //Takes a text file with a list of words and then populates the wordArray with them
    public WordArray(Context context, String file) {

        long startTime = System.nanoTime();

        Log.i("WordArray", "Start of WordArray");

        try {
            InputStream istream = context.getAssets().open(file);
            Log.i("WordArray", "Opened file");
            Log.i("WordArray", Long.toString(System.nanoTime() - startTime));
            BufferedReader reader = new BufferedReader(new InputStreamReader(istream));
            Log.i("WordArray", "Input file to buffer");
            Log.i("WordArray", Long.toString(System.nanoTime() - startTime));
            String strLine;

            while ((strLine = reader.readLine()) != null){
                //  System.out.println(strLine);

                wordArray.add(strLine);
            }


            istream.close();
            Log.i("WordArray", "Closed istream");
            Log.i("WordArray", Long.toString(System.nanoTime() - startTime));
        } catch (IOException e){
            Log.i("WordArray", "IOException");
            e.printStackTrace();
        }
    }

    private int getIndex() {

        Random rand = new Random();
        int index = rand.nextInt(wordArray.size());
        for (int i = 0; i < lastWords.length; i++){
            if (index == lastWords[i]){
                getIndex();
                break;
            }
        }
        if (lastWordsCounter < lastWords.length){
            lastWords[lastWordsCounter] = index;
            lastWordsCounter++;
        }  else {
            lastWordsCounter = 0;
            lastWords[lastWordsCounter] = index;
        }

        return index;
    }

    public String getWord(){

        try {
            return wordArray.get(getIndex());
        } catch (IndexOutOfBoundsException e){
            System.err.println("Caught IndexOutOfBoundsException: " + e.getMessage());

            return "!Error!";
        }
    }
}
