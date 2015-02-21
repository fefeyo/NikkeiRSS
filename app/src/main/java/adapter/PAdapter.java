package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.AllContains;
import fragment.LifeContains;
import fragment.MedicalContains;
import fragment.PCContains;
import fragment.RecommendContains;

/**
 * Created by Sam on 2015/02/21.
 */
public class PAdapter extends FragmentPagerAdapter{
    public PAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:return new AllContains();
            case 1:return new RecommendContains();
            case 2:return new LifeContains();
            case 3:return new MedicalContains();
            case 4:return new PCContains();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:return "すべて";
            case 1:return "おすすめ";
            case 2:return "PC関連";
            case 3:return "生活";
            case 4:return "医療";
        }
        return null;
    }
}
