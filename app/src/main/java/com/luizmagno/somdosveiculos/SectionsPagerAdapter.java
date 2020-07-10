package com.luizmagno.somdosveiculos;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.luizmagno.somdosveiculos.fragments.TerrestresFragment;
import com.luizmagno.somdosveiculos.fragments.AereosFragment;
import com.luizmagno.somdosveiculos.fragments.AquaticosFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private static final int[] TAB_ICONS = new int[]{R.drawable.ic_car, R.drawable.ic_airplane, R.drawable.ic_boat};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);
        switch (position){
            case 0:
                return new TerrestresFragment();
            case 1:
                return new AereosFragment();
            case 2:
                return new AquaticosFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    public Drawable getDrawable(int position) {
        return mContext.getResources().getDrawable(TAB_ICONS[position]);
    }
}