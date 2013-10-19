package com.morningforge.sentenceoriser;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ViewAnimator;

import com.morningforge.sentenceoriser.sentences.sections.*;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    private SectionWins sectionWins = new SectionWins(MainActivity.this);
    private SectionTrapped sectionTrapped = new SectionTrapped(MainActivity.this);

    static private boolean settingsActive;
    static private ViewAnimator viewAnimator;

    CustomViewPager mViewPager;

    private ArrayList<Integer> history = new ArrayList();

    public void addHistory (int position){
        if (getHistorySize() == 0){
            history.add(position);
        } else if (getLastestHistory() != position){
            history.add(position);
        }
    }

    public void removeHistory(){
        history.remove(history.size() - 1);
        history.trimToSize();
    }

    public int getHistorySize(){
        return history.size();
    }

    public int getLastestHistory(){
        return history.get(getHistorySize() - 1);
    }

    static public void setSettingsActive(){
        if (settingsActive){
            settingsActive = false;
        } else {
            settingsActive = true;
        }
    }

    static public void setViewAnimator(ViewAnimator vA){
        viewAnimator = vA;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setTitle("");

        addHistory(0);

      //  actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                addHistory(position);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (settingsActive){
            switch (getLastestHistory()){
                case 0:

                case 1:

                case 2:
                    sectionWins.onBackPressed(viewAnimator);
                case 3:
                    sectionTrapped.onBackPressed(viewAnimator);
                case 4:

                case 5:
            }
        } else {
            if (getHistorySize() <= 1) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.
                removeHistory();
                super.onBackPressed();
            } else {
                // Otherwise, select the previous step.
                removeHistory();
                mViewPager.setCurrentItem(getLastestHistory());
                setViewPagerSwipe(true);
            }
        }
    }

    public void setCurrentPagerItem(int item) {
        mViewPager.setCurrentItem(item, false);
    }

    public void setViewPagerSwipe(boolean enabled){
        mViewPager.setPagingEnabled(enabled);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Context c = MainActivity.this;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new SectionGrid(c);
                case 1:
                    return new SectionRand(c);
                case 2:
                    return sectionWins;
                case 3:
                    return sectionTrapped;
                case 4:
                    return new SectionDescribe(c);
                case 5:
                    return new SectionSettingsTest(c);
                default: return new SectionGrid(c);
            }
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section5).toUpperCase(l);
            }
            return null;
        }
    }
}