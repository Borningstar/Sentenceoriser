package com.morningforge.sentenceoriser.sentences.sections;

/**
 * Created by Ben on 23/05/13.
**/
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import com.morningforge.sentenceoriser.MainActivity;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.sentences.topics.TopicGenerator;

public class SectionWins extends Fragment {
    Context c;

    private TopicGenerator topicGenerator;
    private String sentence;
    private String customSubject = "";
    ScaleGestureDetector scaleGestureDetector;

    public SectionWins(Context c) {
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        topicGenerator = new TopicGenerator(this.c);
        setHasOptionsMenu(true);

        scaleGestureDetector = new ScaleGestureDetector(c, new ScaleListener());

        View rootView = inflater.inflate(R.layout.section_wins, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textViewWins);
        if (customSubject != ""){
        textView.setText(topicGenerator.generateTopic(2));
        } else {
            textView.setText(topicGenerator.generateTopic(2, customSubject));
        }

        sentence = textView.getText().toString();
        textView.setOnTouchListener(touchListener);
        return rootView;
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eventAction = event.getAction();
            TextView textView = (TextView) v.findViewById(R.id.textViewWins);

            scaleGestureDetector.onTouchEvent(event);

            switch (eventAction){
                case MotionEvent.ACTION_UP:
                    textView.setVisibility(TextView.VISIBLE);
                    if (customSubject == ""){
                        textView.setText(topicGenerator.generateTopic(2));
                    } else {
                        textView.setText(topicGenerator.generateTopic(2, customSubject));
                    }
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

    public void share (){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sentence);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void getTextDialog(){

        View v = this.getView();
        final TextView textView = (TextView) v.findViewById(R.id.textViewWins);

        AlertDialog.Builder alert = new AlertDialog.Builder(c);

        alert.setTitle("Set the protagonist");


        final EditText input = new EditText(c);
        input.setText(customSubject);
        alert.setView(input);

        alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                customSubject = String.valueOf(input.getText());
                textView.setText(topicGenerator.generateTopic(2, customSubject));
            }
        });

        alert.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                customSubject = "";
                textView.setText(topicGenerator.generateTopic(2));
            }
        });

        alert.show();
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
        inflater.inflate(R.menu.section_wins_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.share:
                share();
                return true;
            case R.id.settings:
                getTextDialog();
                return true;
            default:
                return true;
        }
    }
}
