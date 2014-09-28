package com.supergod.adapter;

import android.support.v4.view.ViewPager;

/**
 * Created by acer on 2014/9/26.
 */
public class SuperPageAdapter implements ViewPager.OnPageChangeListener {
    private float curPositionOffset = 0;
    private int totalPages;

    public SuperPageAdapter(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (curPositionOffset-positionOffset==0){
            if (position==0)onPageSelectedOverLeft();
            else if(position==totalPages-1) onPageSelectedOverRight();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 将要选择的item大于最大item数时，调用此方法
     */
    public void onPageSelectedOverRight(){

    }
    /**
     * 将要选择的item小于最0时，调用此方法
     */
    public void onPageSelectedOverLeft(){

    }
}
