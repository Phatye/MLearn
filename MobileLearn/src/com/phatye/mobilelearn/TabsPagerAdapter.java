package com.phatye.mobilelearn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
	Bundle arguments;
	
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    public TabsPagerAdapter(FragmentManager fm,
			Bundle arguments) {
    	super(fm);
    	this.arguments = arguments;
	}

	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	LocalResourceFragment fragment = new LocalResourceFragment();
        	if(arguments != null) fragment.setArguments(arguments);
            return  fragment;
        case 1:
        	// Top Rated fragment activity
        	ResourceDetailFragment dfragment = new ResourceDetailFragment();
        	if(arguments != null) dfragment.setArguments(arguments);
            return  dfragment;
        //case 2:
            // Movies fragment activity
            //return new MoviesFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}