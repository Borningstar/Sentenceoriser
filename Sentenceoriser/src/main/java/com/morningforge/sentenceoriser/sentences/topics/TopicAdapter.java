package com.morningforge.sentenceoriser.sentences.topics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by Ben on 19/06/13.
 */
public class TopicAdapter extends BaseAdapter {

    private Context mContext;


    static final String[] numbers = new String[] {
            "Random", "Who Wins?", "Trapped",
            "Explain", "Test", "Test",
            "Test", "Test", "Test",
            "Test", "Test", "Test",
            "Test", "Test", "Test",};

    public TopicAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return numbers.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(200, 200));
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setPadding(8, 8, 8, 8);
            textView.setText(numbers[position]);

            switch (position){
                case 0: textView.setTextColor(Color.parseColor("#FF800D"));
                    break;
                case 1: textView.setTextColor(Color.parseColor("#2DC800"));
                    break;
                case 2: textView.setTextColor(Color.parseColor("#872187"));
                    break;
                case 3: textView.setTextColor(Color.parseColor("#FF2626"));
                    break;
            }

        } else {
            textView = (TextView) convertView;
        }

        return textView;
    }

}
