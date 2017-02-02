package com.yemyatthu.lomotifmockup.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.thebluealliance.spectrum.SpectrumDialog;
import com.yemyatthu.lomotifmockup.BaseActivity;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.FilterAdapter;
import com.yemyatthu.lomotifmockup.presenter.StickerAdapter;
import com.yemyatthu.lomotifmockup.util.FontPickerDialog;
import com.yemyatthu.lomotifmockup.util.PlayerControl;
import com.yemyatthu.lomotifmockup.view.AddPhotoVideoView;
import com.yemyatthu.lomotifmockup.view.AdjustView;
import com.yemyatthu.lomotifmockup.view.TextChangeView;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class CreateVideoActivity extends BaseActivity implements Runnable, FontPickerDialog.FontPickerDialogListener {


    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.download)
    ImageView download;
    @BindView(R.id.video)
    SurfaceView video;
    @BindView(R.id.edit_cont)
    FrameLayout editCont;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.play)
    ImageView play;
    @BindColor(R.color.colorPrimary)
    int colorPrimary;
    @BindColor(R.color.pale)
    int paleColor;
    @BindView(R.id.seek)
    MaterialProgressBar seek;
    @BindView(R.id.add_photo_music)
    AddPhotoVideoView addPhotoMusic;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.text_change_view)
    TextChangeView textChangeView;
    @BindView(R.id.adjust_view)
    AdjustView adjustView;

    private SimpleExoPlayer player;
    private PlayerControl playerControl;
    private FilterAdapter filterAdapter;
    private StickerAdapter stickerAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_create_video;
    }

    @Override
    public void initComponents() {
        close.setOnClickListener(v -> {
            finish();
        });
        download.setOnClickListener(v -> {
            startActivity(new Intent(CreateVideoActivity.this, HomeActivity.class));
            finish();
        });
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        filterAdapter = new FilterAdapter();
        stickerAdapter = new StickerAdapter();
        Handler mainHandler = new Handler();
        TrackSelector trackSelector =
                new DefaultTrackSelector(mainHandler);

// 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();

// 3. Create the player

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "LomotifMockUp"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource videoSource = new ExtractorMediaSource(Uri.parse("asset:///video.mp4"),
                dataSourceFactory, extractorsFactory, null, null);
        player.prepare(videoSource);
        player.setVideoSurfaceView(video);
        playerControl = new PlayerControl(player);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Add", R.drawable.ic_add);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Filter", R.drawable.ic_filter);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Sticker", R.drawable.ic_sticker);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Text", R.drawable.ic_text);

        AHBottomNavigationItem item6 = new AHBottomNavigationItem("Adjust", R.drawable.ic_adjust);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item6);

        bottomNavigation.setAccentColor(colorPrimary);
        bottomNavigation.setInactiveColor(paleColor);
        bottomNavigation.setBackgroundColor(Color.WHITE);
        bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            if (wasSelected)
                return false;
            switch (position) {
                case 0:
                    addPhotoMusic.setVisibility(View.VISIBLE);
                    recycler.setVisibility(View.GONE);
                    textChangeView.setVisibility(View.GONE);
                    adjustView.setVisibility(View.GONE);
                    return true;
                case 1:
                    recycler.setVisibility(View.VISIBLE);
                    addPhotoMusic.setVisibility(View.GONE);
                    textChangeView.setVisibility(View.GONE);
                    adjustView.setVisibility(View.GONE);
                    recycler.setAdapter(filterAdapter);
                    return true;
                case 2:
                    recycler.setVisibility(View.VISIBLE);
                    addPhotoMusic.setVisibility(View.GONE);
                    textChangeView.setVisibility(View.GONE);
                    adjustView.setVisibility(View.GONE);
                    recycler.setAdapter(stickerAdapter);
                    return true;
                case 3:
                    addPhotoMusic.setVisibility(View.GONE);
                    recycler.setVisibility(View.GONE);
                    textChangeView.setVisibility(View.VISIBLE);
                    adjustView.setVisibility(View.GONE);
                    textChangeView.setOnFontPickListener(v -> new FontPickerDialog()
                            .show(getFragmentManager(), "fontPicker"));
                    textChangeView.setOnColorPickListener(v -> new SpectrumDialog.Builder(CreateVideoActivity.this)
                            .setColors(new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.MAGENTA, Color.YELLOW})
                            .build().show(getSupportFragmentManager(), "colorPicker"));
                    return true;
                case 4:
                    recycler.setVisibility(View.GONE);
                    addPhotoMusic.setVisibility(View.GONE);
                    textChangeView.setVisibility(View.GONE);
                    adjustView.setVisibility(View.VISIBLE);
                    return true;
                default:
                    return false;
            }
        });
        player.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    play.setVisibility(View.VISIBLE);
                    playerControl.seekTo(0);
                    playerControl.pause();
                }
            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                play.setVisibility(View.VISIBLE);
                playerControl.seekTo(0);
                playerControl.pause();
            }

            @Override
            public void onPositionDiscontinuity() {

            }
        });
        video.setOnClickListener(v -> {
            if (playerControl.isPlaying()) {
                play.setVisibility(View.VISIBLE);
                playerControl.pause();
            } else {
                if (playerControl.getCurrentPosition() == 0) {
                    play.setVisibility(View.GONE);
                    playerControl.start();
                    seek.setProgress(0);
                    seek.setMax(playerControl.getDuration());
                    new Thread(this).start();
                } else if (playerControl.getCurrentPosition() < playerControl.getDuration()) {
                    play.setVisibility(View.GONE);
                    playerControl.start();
                } else {
                    play.setVisibility(View.VISIBLE);
                    playerControl.seekTo(0);
                    playerControl.pause();
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        player.release();
    }

    @Override
    public void run() {
        int currentPosition = 0;
        int total = playerControl.getDuration();
        while (playerControl != null && currentPosition < total) {
            try {
                Thread.sleep(100);
                currentPosition = playerControl.getCurrentPosition();
            } catch (Exception e) {
                return;
            }

            seek.setProgress(currentPosition);
        }
    }

    @Override
    public void onFontSelected(FontPickerDialog dialog) {
        dialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
