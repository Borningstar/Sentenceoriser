package com.morningforge.sentenceoriser.sentences.sections;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.expandListView.Adapter.ExpandListAdapter;
import com.morningforge.sentenceoriser.expandListView.Classes.ExpandListChild;
import com.morningforge.sentenceoriser.expandListView.Classes.ExpandListGroup;

import java.util.ArrayList;

/**
 * Created by Ben on 23/07/13.
 */
public class SectionListViewTest extends Fragment {

    /** Called when the activity is first created. */
    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;
    private Context c;

    public SectionListViewTest(Context c){
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.listviewlayout, container, false);
        ExpandList = (ExpandableListView) rootView.findViewById(R.id.ExpList);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(c, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

        return rootView;
    }

    public ArrayList<ExpandListGroup> SetStandardGroups() {
        ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
        ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setSentence("You're trapped in An Arena");
        ExpandListChild ch1_1 = new ExpandListChild();
        ch1_1.setSentence("An Arena");
        ch1_1.setMode(0);
        list2.add(ch1_1);
        gru1.setItems(list2);
        list2 = new ArrayList<ExpandListChild>();

        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setSentence("which is in Situation");
        ExpandListChild ch2_1 = new ExpandListChild();
        ch2_1.setSentence("Situation");
        ch2_1.setMode(0);
        list2.add(ch2_1);
        gru2.setItems(list2);
        list.add(gru1);
        list.add(gru2);

        return list;
    }


}
