package harelchuk.maxim.quizwithmoxy.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import harelchuk.maxim.quizwithmoxy.fragments.SetUsersBooksFilmsFragment;
import harelchuk.maxim.quizwithmoxy.fragments.SetEmblemFragment;
import harelchuk.maxim.quizwithmoxy.fragments.SetBankFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
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
