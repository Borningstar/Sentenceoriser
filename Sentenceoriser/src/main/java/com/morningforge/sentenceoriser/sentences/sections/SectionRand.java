package com.morningforge.sentenceoriser.sentences.sections;

/**
 * Created by Ben on 23/05/13.
**/
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import com.morningforge.sentenceoriser.MainActivity;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.sentences.topics.TopicGenerator;

public class SectionRand extends Fragment {
    Context c;

    ScaleGestureDetector scaleGestureDetector;
    private TopicGenerator topicGenerator;
    private String sentence;

    public SectionRand(){

    }
    public SectionRand(Context c) {
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        topicGenerator = new TopicGenerator(this.c);
        setHasOptionsMenu(true);

        scaleGestureDetector = new ScaleGestureDetector(c, new ScaleListener());

        View rootView = inflater.inflate(R.layout.section_rand, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textViewRand);
        textView.setText(topicGenerator.generateTopic(0));
        sentence = textView.getText().toString();
        textView.setOnTouchListener(touchListener);
        return rootView;
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eventAction = event.getAction();
            TextView textView = (TextView) v.findViewById(R.id.textViewRand);

            scaleGestureDetector.onTouchEvent(event);

            switch (eventAction){
                case MotionEvent.ACTION_UP:
                    textView.setVisibility(TextView.VISIBLE);
                    textView.setText(topicGenerator.generateTopic(0));
                    sentence = textView.getText().toString();
                    break;
                case MotionEvent.ACTION_DOWN:
                    textView.setVisibility(TextView.INVISIBLE);
                    break;
                default:
                    textView.setVisibility(TextView.VISIBLE);
            }
            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    private void share (){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sentence);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            ((MainActivity)getActivity()).setCurrentPagerItem(0);
            return true;
        }
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.share:
                share();
                Log.i("Menu", "ClickedShare");
                return true;
            default:
                Log.i("Menu", "Clicked");
                return true;
        }
    }
}
