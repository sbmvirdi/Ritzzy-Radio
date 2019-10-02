package cf.rittzyradio.ritzzyradio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1: Dashboard dashboard = new Dashboard();
                return dashboard;
            case 2: Notification notifications = new Notification();
                return notifications;
            case 0:Home home = new Home();
                return home;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
