package id.ac.budiluhur.qrbeta3;

/**
 * Created by Muhammad Saddam Nur on 4/19/2017.
 */

/*untuk berpindah page di tab*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 tab1 = new tab1();
                return tab1;
            case 1:
                tab2 tab2 = new tab2();
                return tab2;
            case 2:
                tab3 tab3 = new tab3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}