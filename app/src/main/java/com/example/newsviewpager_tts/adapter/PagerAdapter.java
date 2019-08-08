package com.example.newsviewpager_tts.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.newsviewpager_tts.fragment.ListPostFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private String listTitle[]  = {"Công nghệ","Thể Thao","Xe cộ","Giáo dục","Giải trí","Kinh Tế","Thế giới"};
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new ListPostFragment("https://baomoi.com/khoa-hoc-cong-nghe.epi");
            case 1: return new ListPostFragment("https://baomoi.com/the-thao.epi");
            case 2: return new ListPostFragment("https://baomoi.com/xe-co.epi");
            case 3: return new ListPostFragment("https://baomoi.com/giao-duc.epi");
            case 4: return new ListPostFragment("https://baomoi.com/giai-tri.epi");
            case 5: return new ListPostFragment("https://baomoi.com/kinh-te.epi");
            case 6: return new ListPostFragment("https://baomoi.com/the-gioi.epi");
        }
        return new ListPostFragment("https://baomoi.com/xe-co.epi");
    }

    @Override
    public int getCount() {
        return listTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle[position];
    }
}
