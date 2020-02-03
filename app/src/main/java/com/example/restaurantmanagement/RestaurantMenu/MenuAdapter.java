package com.example.restaurantmanagement.RestaurantMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private List<Fragment> menuFragment;

    public MenuAdapter(@NonNull FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        menuFragment = new ArrayList<>();
        menuFragment.add(new FoodMenuFragment());
        titles.add("Món ăn");
    }

    public MenuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return menuFragment.get(position);
    }

    @Override
    public int getCount() {
        return menuFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
