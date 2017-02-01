package com.yemyatthu.lomotifmockup.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.yemyatthu.lomotifmockup.BaseActivity;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.AlbumEvent;
import com.yemyatthu.lomotifmockup.event.MotifEvent;
import com.yemyatthu.lomotifmockup.presenter.TabPagerAdapter;
import com.yemyatthu.lomotifmockup.util.AlbumFragmentViewController;
import com.yemyatthu.lomotifmockup.util.FadePageTransformer;
import com.yemyatthu.lomotifmockup.util.MotifFragmentViewController;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.content_home)
    RelativeLayout contentHome;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;
    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;
    @BindColor(R.color.pale)
    int paleColor;
    private TabPagerAdapter tabPagerAdapter;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private AddFragment addFragment;
    public AlbumFragmentViewController albumFragmentViewController;
    public MotifFragmentViewController motifFragmentViewController;
    private int lastCurrentItem = 0;
    @Override
    public int getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initComponents() {
        albumFragmentViewController = new AlbumFragmentViewController();
        motifFragmentViewController = new MotifFragmentViewController();
        fragmentManager = getSupportFragmentManager();
        addFragment = AddFragment.newInstance();
        addFragment.setOnDismissListener(() -> {
            bottomNavigation.setCurrentItem(lastCurrentItem);
        });
        fragments = new ArrayList<>();
        fragments.add(FeedFragment.newInstance());
        fragments.add(addFragment);
        fragments.add(UserFragment.newInstance());
        fragments.add(NotificationsFragment.newInstance());


        tabPagerAdapter = new TabPagerAdapter(fragmentManager, fragments,null);
        pager.setAdapter(tabPagerAdapter);
        pager.setPageTransformer(false, new FadePageTransformer());
        pager.setOffscreenPageLimit(3);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Feed", R.drawable.ic_feed);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Add", R.drawable.ic_add);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Profile", R.drawable.ic_profile);
        AHBottomNavigationItem notiItem = new AHBottomNavigationItem("Notifications",R.drawable.ic_noti);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(notiItem);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        bottomNavigation.setAccentColor(colorPrimary);
        bottomNavigation.setInactiveColor(paleColor);
        bottomNavigation.setBackgroundColor(Color.WHITE);
        bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        AHNotification ahNotification = new AHNotification.Builder()
                .setText("4")
                .setBackgroundColor(Color.RED).build();
        bottomNavigation.setNotification(ahNotification,3);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            if (wasSelected)
                return false;
            pager.setCurrentItem(position, false);
            if (position != 1) {
                lastCurrentItem = position;
            }
            return true;
        });

        pager.setCurrentItem(1);
        bottomNavigation.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {

        if (albumFragmentViewController.isFirstView() && motifFragmentViewController.isFirstView()) {
            super.onBackPressed();
        } else if (!albumFragmentViewController.isFirstView()) {
            EventBus.getDefault().post(new AlbumEvent());
        } else if (!motifFragmentViewController.isFirstView()) {
            EventBus.getDefault().post(new MotifEvent());
        }
    }
}
