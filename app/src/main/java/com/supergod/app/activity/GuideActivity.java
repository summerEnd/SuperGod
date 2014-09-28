package com.supergod.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class GuideActivity extends Activity {
    ViewPager pager;
    ArrayList<View> views = new ArrayList<View>();
    private final int drawableId[] = new int[]{1, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFirstStart()) {
            pager = new ViewPager(this);
            pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            for (int i = 0; i < drawableId.length; i++) views.add(getImage(drawableId[i]));
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            views.add(imageView);
            pager.setAdapter(new GuideAdapter());
            pager.setOnPageChangeListener(new GuidePageChangeListener());
        } else {
            setLoading();
        }
    }

    private ImageView getImage(int id) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(id);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }


    private class GuidePageChangeListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == views.size()-1) {
                setFirstStart(false);
                setLoading();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

    private void setLoading() {


        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(2000);    //暂停两秒  可以用来加载首页数据.
                } catch (InterruptedException e) {

                }

                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                startActivity(new Intent(GuideActivity.this, null));
                GuideActivity.this.finish();
            }
        }
    };

    private boolean isFirstStart() {
        return getSharedPreferences("config", MODE_PRIVATE).getBoolean("isFirstStart", true);
    }

    private void setFirstStart(boolean isFirstStart) {
        getSharedPreferences("config", MODE_PRIVATE).edit().putBoolean("isFirstStart", isFirstStart);
    }
}
