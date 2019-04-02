package harelchuk.maxim.morgulis.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import harelchuk.maxim.morgulis.fragments.SetUsersBooksFilmsFragment;
import harelchuk.maxim.morgulis.fragments.SetEmblemFragment;
import harelchuk.maxim.morgulis.fragments.SetBankFragment;

public class SettingsPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public SettingsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SetUsersBooksFilmsFragment();
            case 1:
                return new SetEmblemFragment();
            case 2:
                return new SetBankFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
