package com.yemyatthu.lomotifmockup.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yemyatthu.lomotifmockup.BaseActivity;
import com.yemyatthu.lomotifmockup.R;

import butterknife.BindView;

public class FriendsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_friends;
    }

    @Override
    public void initComponents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Find Friends");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
