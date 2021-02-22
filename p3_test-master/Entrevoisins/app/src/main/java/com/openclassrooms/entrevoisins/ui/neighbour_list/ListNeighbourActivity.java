package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity  {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO:test  transistion
        overridePendingTransition(R.xml.fadein, R.xml.fadeout);


        setContentView(R.layout.activity_list_neighbour);

        ButterKnife.bind(this);

        //add the return button
        setSupportActionBar(mToolbar);

        //Création de l'adaptateur avec la class ListNeighbourPagerAdapter
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());

        //Association de l'adaptateur au ViewPAger
        mViewPager.setAdapter(mPagerAdapter);

        //add a listener to ViewPAger and synchronize the tab with selected page
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //Add a listener to TabLAyout when the tab change and synchronize the tab with the viewpager page
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //just for testing transitions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
        {
            Log.i("THOMAS", "Prise en charge des transisiotns possible...");
        }else
        {
            Log.i("THOMAS", "Prise en charge des transisiotns impossible sur cette version");
        }


    }

    //EventBus
    @OnClick(R.id.add_neighbour)
    void addNeighbour()
    {
        AddNeighbourActivity.navigate(this);
    }



}
