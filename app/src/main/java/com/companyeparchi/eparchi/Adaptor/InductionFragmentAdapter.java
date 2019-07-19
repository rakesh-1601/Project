package com.companyeparchi.eparchi.Adaptor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.companyeparchi.eparchi.Activities.Fragmentfurnace1;
import com.companyeparchi.eparchi.Activities.Fragmentfurnace2;
import com.companyeparchi.eparchi.Activities.Fragmentfurnace3;
import com.companyeparchi.eparchi.Activities.Fragmentfurnace4;

public class InductionFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] childFragments;
    private String[] titles = {"Furnace 1","Furnace 2","Furnace 3","Furnace 4"};

    public InductionFragmentAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new Fragmentfurnace1(), //0
                new Fragmentfurnace2(), //1
                new Fragmentfurnace3(), //2
                new Fragmentfurnace4()  //3
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length; //3 items
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

