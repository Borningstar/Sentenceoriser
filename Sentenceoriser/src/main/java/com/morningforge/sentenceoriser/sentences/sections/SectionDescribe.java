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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.morningforge.sentenceoriser.MainActivity;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.sentences.topics.TopicDescribe;

public class SectionDescribe extends Fragment {
    private Context c;
    private String sentence;
    private TopicDescribe topicGenerator;
    private ScaleGestureDetector scaleGestureDetector;
    private View rootView;


    private ViewSwitcher viewSwitcher;

    public SectionDescribe(){

    }
    public SectionDescribe(Context c) {
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.section_describe, container, false);
        viewSwitcher = (ViewSwitcher)rootView.findViewById(R.id.viewSwitcher1);

        topicGenerator = new TopicDescribe(this.c);

        setHasOptionsMenu(true);

        scaleGestureDetector = new ScaleGestureDetector(c, new ScaleListener());

        TextView textView = (TextView) rootView.findViewById(R.id.textViewDescribe);
        textView.setText(topicGenerator.generateTopic());
        sentence = textView.getText().toString();
        textView.setOnTouchListener(touchListener);

        Button resetButton = (Button)rootView.findViewById(R.id.describeButtonReset);
        Button okButton = (Button)rootView.findViewById(R.id.describeButtonOK);

        //Reset Button
        resetButton.setOnClickListener(
        new View.OnClickListener(){
            public void onClick(View view){
                Switch switchButton;
                EditText editText;
                CheckBox checkBox;

                //Topic
                topicGenerator.setTopicCustom(false);
                topicGenerator.setTopic("");
                checkBox = (CheckBox)rootView.findViewById(R.id.describeTopicChecked);
                checkBox.setChecked(false);
                editText = (EditText)rootView.findViewById(R.id.describeTopicEdit);
                editText.setText("");
                //Character 1
                topicGenerator.setCharacter1Custom(false);
                topicGenerator.setCharacter1("");
                settingsReset(((Switch)rootView.findViewById(R.id.describeCharacter1Switch)),
                        ((EditText)rootView.findViewById(R.id.describeCharacter1Edit)),
                        ((CheckBox)rootView.findViewById(R.id.describeCharacter1Checked)));

                //Character 2
                topicGenerator.setCharacter2Custom(false);
                topicGenerator.setCharacter2("");
                settingsReset(((Switch)rootView.findViewById(R.id.describeCharacter2Switch)),
                        ((EditText)rootView.findViewById(R.id.describeCharacter2Checked)),
                        ((CheckBox)rootView.findViewById(R.id.describeCharacter2Edit)));
                //Stat you
                topicGenerator.setStatYouCustom(false);
                topicGenerator.setStatYou("");
                settingsReset(((Switch)rootView.findViewById(R.id.describeStatusYouSwitch)),
                        ((EditText)rootView.findViewById(R.id.describeStatusYouBox)),
                        ((CheckBox)rootView.findViewById(R.id.describeStatusYouEdit)));
                //Stat me
                topicGenerator.setStatMeCustom(false);
                topicGenerator.setStatusMe("");
                settingsReset(((Switch)rootView.findViewById(R.id.describeStatusMeSwitch)),
                        ((EditText)rootView.findViewById(R.id.describeStatusMeBox)),
                        ((CheckBox)rootView.findViewById(R.id.describeStatusMeEdit)));
                //Stat us
                topicGenerator.setStatUsCustom(false);
                topicGenerator.setStatUs("");
                settingsReset(((Switch)rootView.findViewById(R.id.describeStatusUsSwitch)),
                        ((EditText)rootView.findViewById(R.id.describeStatusUsBox)),
                        ((CheckBox)rootView.findViewById(R.id.describeStatusUsEdit)));
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
                editText = (EditText)rootView.findViewById(R.id.describeTopicEdit);
                topicGenerator.setTopic(editText.getText().toString());
                //Set character 1 text
                editText = (EditText)rootView.findViewById(R.id.describeCharacter1Edit);
                topicGenerator.setCharacter1(editText.getText().toString());
                //Set character 2 text
                editText = (EditText)rootView.findViewById(R.id.describeCharacter2Edit);
                topicGenerator.setCharacter2(editText.getText().toString());
                //Set stat you text
                editText = (EditText)rootView.findViewById(R.id.describeStatusYouEdit);
                topicGenerator.setStatYou(editText.getText().toString());
                //Set stat me text
                editText = (EditText)rootView.findViewById(R.id.describeStatusMeEdit);
                topicGenerator.setStatusMe(editText.getText().toString());
                //Set stat us text
                editText = (EditText)rootView.findViewById(R.id.describeStatusUsEdit);
                topicGenerator.setStatUs(editText.getText().toString());

                //Check switches//
                //Character 1 switch
                switchButton = (Switch)rootView.findViewById(R.id.describeCharacter1Switch);
                if (switchButton.isChecked()){
                    topicGenerator.setCharacter1Active(true);
                }   else{
                    topicGenerator.setCharacter1Active(false);
                }
                //Character 2 switch
                switchButton = (Switch)rootView.findViewById(R.id.describeCharacter2Switch);
                if (switchButton.isChecked()){
                    topicGenerator.setCharacter2Active(true);
                }   else{
                    topicGenerator.setCharacter2Active(false);
                }
                //Status me switch
                switchButton = (Switch)rootView.findViewById(R.id.describeStatusMeSwitch);
                if (switchButton.isChecked()){
                    topicGenerator.setStatMeActive(true);
                }   else{
                    topicGenerator.setStatMeActive(false);
                }
                //Status us switch
                switchButton = (Switch)rootView.findViewById(R.id.describeStatusUsSwitch);
                if (switchButton.isChecked()){
                    topicGenerator.setStatUsActive(true);
                }   else{
                    topicGenerator.setStatUsActive(false);
                }
                //Status you switch
                switchButton = (Switch)rootView.findViewById(R.id.describeStatusYouSwitch);
                if (switchButton.isChecked()){
                    topicGenerator.setStatYouActive(true);
                }   else{
                    topicGenerator.setStatYouActive(false);
                }

                //Check checkboxes//
                //Topic checkbox
                checkBox = (CheckBox)rootView.findViewById(R.id.describeTopicChecked);
                if (checkBox.isChecked()){
                    topicGenerator.setTopicCustom(true);
                } else{
                    topicGenerator.setTopicCustom(false);
                }
                //Character 1 checkbox
                checkBox = (CheckBox)rootView.findViewById(R.id.describeCharacter1Checked);
                if (checkBox.isChecked()){
                    topicGenerator.setCharacter1Custom(true);
                } else{
                    topicGenerator.setCharacter1Custom(false);
                }
                //Character 2 checkbox
                checkBox = (CheckBox)rootView.findViewById(R.id.describeCharacter2Checked);
                if (checkBox.isChecked()){
                    topicGenerator.setCharacter2Custom(true);
                } else{
                    topicGenerator.setCharacter2Custom(false);
                }
                //Status Me checkbox
                checkBox = (CheckBox)rootView.findViewById(R.id.describeStatusMeBox);
                if (checkBox.isChecked()){
                    topicGenerator.setStatMeCustom(true);
                } else{
                    topicGenerator.setStatMeCustom(false);
                }
                //Status You checkbox
                checkBox = (CheckBox)rootView.findViewById(R.id.describeStatusYouBox);
                if (checkBox.isChecked()){
                    topicGenerator.setStatYouCustom(true);
                } else{
                    topicGenerator.setStatYouCustom(false);
                }
                //Status Us checkbox
                checkBox = (CheckBox)rootView.findViewById(R.id.describeStatusUsBox);
                if (checkBox.isChecked()){
                    topicGenerator.setStatUsCustom(true);
                } else{
                    topicGenerator.setStatUsCustom(false);
                }

                ((MainActivity)getActivity()).setViewPagerSwipe(true);
                setHasOptionsMenu(true);
                Customise();
            }
        });
        return rootView;
    }

    private void settingsReset(Switch switchButton, EditText editText,CheckBox checkBox){
        switchButton.setChecked(true);
        checkBox.setChecked(false);
        editText.setText("");
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eventAction = event.getAction();
            TextView textView = (TextView) v.findViewById(R.id.textViewDescribe);

            setHasOptionsMenu(true);

            scaleGestureDetector.onTouchEvent(event);

            if (scaleGestureDetector.isInProgress() == false){
                switch (eventAction){
                    case MotionEvent.ACTION_UP:
                        textView.setText(topicGenerator.generateTopic());
                        sentence = textView.getText().toString();
                        textView.setVisibility(TextView.VISIBLE);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        textView.setVisibility(TextView.INVISIBLE);
                        break;
                    default:
                        textView.setVisibility(TextView.VISIBLE);
                }
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

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            ((MainActivity)getActivity()).setCurrentPagerItem(0);
            return true;
        }
    }

    private void Customise(){
        if (viewSwitcher.getCurrentView() != getActivity().findViewById(R.layout.section_describe)){
            viewSwitcher.showNext();
        } else {
            viewSwitcher.showPrevious();
        }
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
           inflater.inflate(R.menu.section_describe_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.share:
                share();
                Log.i("Menu", "ClickedShare");
                return true;
            case R.id.settings:
                setHasOptionsMenu(false);
                ((MainActivity)getActivity()).setViewPagerSwipe(false);
                Customise();
                return true;
            default:
                Log.i("Menu", "Clicked");
                return true;
        }
    }
}
