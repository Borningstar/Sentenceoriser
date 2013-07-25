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
import android.widget.*;
import com.morningforge.sentenceoriser.MainActivity;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.sentences.topics.TopicTrapped;

public class SectionTrapped extends Fragment {
    Context c;
    private String sentence;
    ScaleGestureDetector scaleGestureDetector;
    private ViewSwitcher viewSwitcher;
    private View rootView;

    private TopicTrapped topicTrapped;

    public SectionTrapped(){

    }
    public SectionTrapped(Context c) {
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        topicTrapped = new TopicTrapped(this.c);

        rootView = inflater.inflate(R.layout.section_trapped, container, false);
        viewSwitcher = (ViewSwitcher)rootView.findViewById(R.id.viewSwitcher2);

        setHasOptionsMenu(true);

        scaleGestureDetector = new ScaleGestureDetector(c, new ScaleListener());

        TextView textView = (TextView) rootView.findViewById(R.id.textViewTrapped);
        textView.setText(topicTrapped.generateTopic());
        sentence = textView.getText().toString();
        textView.setOnTouchListener(touchListener);

        final TableRow arenaRow1 = (TableRow)rootView.findViewById(R.id.trappedRowArena1);
        final TableRow arenaRow2 = (TableRow)rootView.findViewById(R.id.trappedRowArena2);

        arenaRow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("ArenaClick", "Clicked, yo");
                arenaRow2.setVisibility(View.VISIBLE);
            }
        });

        arenaRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arenaRow2.setVisibility(View.GONE);
            }
        });

/*
        Button resetButton = (Button)rootView.findViewById(R.id.trappedButtonReset);
        Button okButton = (Button)rootView.findViewById(R.id.trappedButtonOK);

        //Reset Button
        resetButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Switch switchButton;
                        EditText editText;
                        CheckBox checkBox;

                        //Character 1
                        topicTrapped.situation.setAll("", false, true);
                        settingsReset(((Switch)rootView.findViewById(R.id.trappedSituationSwitch)),
                                ((EditText)rootView.findViewById(R.id.trappedSituationEdit)),
                                ((CheckBox)rootView.findViewById(R.id.trappedSituationChecked)));
                    }
                });

        //OK Button
        okButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Switch switchButton;
                        EditText editText;
                        CheckBox checkBox;

                        //Set topic text
                        editText = (EditText)rootView.findViewById(R.id.trappedSituationEdit);
                        topicTrapped.situation.setName(editText.getText().toString());

                        //Check switches//
                        //Character 1 switch
                        switchButton = (Switch)rootView.findViewById(R.id.trappedSituationSwitch);
                        if (switchButton.isChecked()){
                            topicTrapped.situation.setActive(true);
                        }   else{
                            topicTrapped.situation.setActive(false);
                        }

                        //Check checkboxes//
                        //Topic checkbox
                        checkBox = (CheckBox)rootView.findViewById(R.id.trappedSituationChecked);
                        if (checkBox.isChecked()){
                            topicTrapped.situation.setCustom(true);
                        } else{
                            topicTrapped.situation.setCustom(false);
                        }

                        ((MainActivity)getActivity()).setViewPagerSwipe(true);
                        setHasOptionsMenu(true);
                        Customise();
                    }
                }); */
        return rootView;
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eventAction = event.getAction();
            TextView textView = (TextView) v.findViewById(R.id.textViewTrapped);

            scaleGestureDetector.onTouchEvent(event);

            switch (eventAction){
                case MotionEvent.ACTION_UP:
                    textView.setVisibility(TextView.VISIBLE);
                    textView.setText(topicTrapped.generateTopic());
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

    private void settingsReset(Switch switchButton, EditText editText,CheckBox checkBox){
        switchButton.setChecked(true);
        checkBox.setChecked(false);
        editText.setText("");
    }

    public void share (){

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
           inflater.inflate(R.menu.section_describe_menu, menu);
    }

    private void Customise(){
        if (viewSwitcher.getCurrentView() != getActivity().findViewById(R.layout.section_trapped)){
            viewSwitcher.showNext();
        } else {
            viewSwitcher.showPrevious();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.share:
                share();
                Log.i("Menu", "ClickedShare");
                return true;
            case R.id.settings:
                //setHasOptionsMenu(false);
                ((MainActivity)getActivity()).setViewPagerSwipe(false);
                Customise();
                return true;
            default:
                Log.i("Menu", "Clicked");
                return true;
        }
    }


}
