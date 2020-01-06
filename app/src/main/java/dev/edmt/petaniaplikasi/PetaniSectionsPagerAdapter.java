package dev.edmt.petaniaplikasi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Windows on 2/26/2018.
 */

public class PetaniSectionsPagerAdapter extends FragmentPagerAdapter {

    public PetaniSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                petani_pageone pagee1 = new petani_pageone();
                return pagee1;

            case 1:
                petani_pagetwo pagee2 = new petani_pagetwo();
                return pagee2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:

                return "Beranda";
            case 1:
                return "Profile";
            default:
                return null;
        }
    }
}
