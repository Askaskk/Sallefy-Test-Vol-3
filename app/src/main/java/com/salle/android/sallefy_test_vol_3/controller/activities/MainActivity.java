package com.salle.android.sallefy_test_vol_3.controller.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.salle.android.sallefy_test_vol_3.R;
import com.salle.android.sallefy_test_vol_3.controller.callbacks.FragmentCallback;
import com.salle.android.sallefy_test_vol_3.controller.fragments.ContentFragment;
import com.salle.android.sallefy_test_vol_3.controller.fragments.HomeFragment;
import com.salle.android.sallefy_test_vol_3.controller.fragments.SearchFragment;
import com.salle.android.sallefy_test_vol_3.controller.fragments.SongsFragment;

public class MainActivity extends FragmentActivity implements FragmentCallback {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private BottomNavigationView mNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setInitialFragment();
    }

    private void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        mNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = HomeFragment.getInstance();
                        break;
                    case R.id.action_songs:
                        fragment = SongsFragment.getInstance();
                        break;
                    case R.id.action_search:
                        fragment = SearchFragment.getInstance();
                        break;
                    case R.id.action_content:
                        fragment = ContentFragment.getInstance();
                        break;

                }
                replaceFragment(fragment);
                return true;
            }
        });
    }

    private void setInitialFragment() {
        mTransaction.add(R.id.fragment_container, HomeFragment.getInstance());
        mTransaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        String fragmentTag = getFragmentTag(fragment);
        Fragment currentFragment = mFragmentManager.findFragmentByTag(fragmentTag);
        if (currentFragment != null) {
            if (!currentFragment.isVisible()) {

                if (fragment.getArguments() != null) {
                    currentFragment.setArguments(fragment.getArguments());
                }
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, currentFragment, fragmentTag)
                        .addToBackStack(null)
                        .commit();

            }
        } else {
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, fragmentTag)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private String getFragmentTag(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            return HomeFragment.TAG;
        } else {
            if (fragment instanceof SongsFragment) {
                return SongsFragment.TAG;
            } else {
                if (fragment instanceof SearchFragment) {
                    return SearchFragment.TAG;
                } else {
                    return ContentFragment.TAG;
                }
            }
        }
    }

    @Override
    public void onChangeFragment(Fragment fragment) {
        replaceFragment(fragment);
    }
}
