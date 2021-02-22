package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter
{
    public ListNeighbourPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return NeighbourFragment.newInstance();
            case 1:
                return NeighbourFavoritesFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return super.getPageTitle(position);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount()
    {
        return 2;
    }
}