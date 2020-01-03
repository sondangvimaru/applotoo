package com.cnt57cl.phungtrang.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cnt57cl.phungtrang.R;

public class adapter_for_view_tab extends FragmentPagerAdapter {

    Context context;
    public adapter_for_view_tab(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return result_screen.create_view();
            case 1:return raote_try_screen.create_view();
        }
        return result_screen.create_view();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return context.getString(R.string.tab_text_1);
            case 1:return  context.getString(R.string.tab_text_2);
        }
        return context.getString(R.string.tab_text_1);
    }
}
