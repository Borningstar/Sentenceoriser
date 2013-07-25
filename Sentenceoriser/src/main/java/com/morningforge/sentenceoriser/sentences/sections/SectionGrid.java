package com.morningforge.sentenceoriser.sentences.sections;

/**
 * Created by Ben on 23/05/13.
**/
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import com.morningforge.sentenceoriser.MainActivity;
import com.morningforge.sentenceoriser.R;
import com.morningforge.sentenceoriser.sentences.topics.TopicAdapter;

public class SectionGrid extends Fragment {
    Context c;

    GridView gridView;

    public SectionGrid(){

    }
    public SectionGrid(Context c) {
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.section_grid, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);

        gridView.setAdapter(new TopicAdapter(c));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ((MainActivity)getActivity()).setCurrentPagerItem(position + 1);
            }
        });

        return rootView;
    }
}
