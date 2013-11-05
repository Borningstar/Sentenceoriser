package com.morningforge.sentenceoriser;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    private SectionDescribe sectionDescribe = new SectionDescribe(MainActivity.this);

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

        String[] drawerItems = new String[3];
        drawerItems[0] = "Wins";
        drawerItems[1] = "Trapped";
        drawerItems[2] = "Explain";

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        final LinearLayout drawerLayout = (LinearLayout) findViewById(R.id.drawerLayout);
        final ListView drawerList = (ListView) findViewById(R.id.drawerListView);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_view_item, drawerItems));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setCurrentPagerItem(position);
                drawer.closeDrawer(drawerLayout);
            }
        });

      //  actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.pager);
      //  mViewPager.setPivotX(0);
      //  mViewPager.setPivotY(0);
      //  mViewPager.setRotation(90f);

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
                    sectionWins.onBackPressed(viewAnimator);
                case 1:
                    sectionTrapped.onBackPressed(viewAnimator);
                case 2:
                    sectionDescribe.onBackPressed(viewAnimator);
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
        sectionWins.setVisible();
        sectionDescribe.setVisible();
        sectionTrapped.setVisible();
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
                    return sectionWins;
                case 1:
                    return sectionTrapped;
                case 2:
                    return sectionDescribe;
                default:
                    return sectionWins;
            }
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 3;
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
            }
            return null;
        }
    }
}