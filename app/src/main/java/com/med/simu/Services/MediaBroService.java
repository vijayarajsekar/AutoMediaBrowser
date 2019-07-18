package com.med.simu.Services;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.med.simu.R;

import java.util.ArrayList;
import java.util.List;


public class MediaBroService extends MediaBrowserService {

    private BrowserRoot mBrowserRoot;
    private MediaSession mSession;
    private MediaSession.Token mToken;

    private List<MediaBrowser.MediaItem> mMediaItemsList;
    private MediaBrowser.MediaItem mMediaItems;

    private MediaDescription.Builder mMediaDesBuilder;

    private MediaMetadata mMetaData;

    private PlaybackState mPlaybackState;

    @Override
    public void onCreate() {
        super.onCreate();

        mSession = new MediaSession(this, "mSession");
        mSession.setCallback(mCallback);

        mSession.setActive(true);

        mMetaData = new MediaMetadata.Builder().putText(MediaMetadata.METADATA_KEY_AUTHOR, "Taking into Hell").build();
        mSession.setMetadata(mMetaData);

//        mPlaybackState = new PlaybackState.Builder()
//                .setActions(PlaybackState.ACTION_PLAY_PAUSE)
//                .setState(PlaybackState.STATE_PLAYING, 0, 1).build();
//        mSession.setPlaybackState(mPlaybackState);

        mToken = mSession.getSessionToken();
        setSessionToken(mToken);
    }

    @Override
    public BrowserRoot onGetRoot(String s, int i, Bundle bundle) {
        mBrowserRoot = new BrowserRoot("testRootId", null);
        return mBrowserRoot;
    }

    @Override
    public void onLoadChildren(String rootId, Result<List<MediaBrowser.MediaItem>> result) {

        mMediaItemsList = new ArrayList<>();

        if (rootId.equals("testRootId")) {

            // MediaItem - Type 1
            mMediaDesBuilder = new MediaDescription.Builder();
            mMediaDesBuilder.setDescription("Group Desc 1");
            mMediaDesBuilder.setTitle("Media Group 1");
            mMediaDesBuilder.setMediaId("MediaGroup1");
            mMediaDesBuilder.setIconBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_round_donut));

            mMediaItems = new MediaBrowser.MediaItem(mMediaDesBuilder.build(), MediaBrowser.MediaItem.FLAG_BROWSABLE);
            mMediaItemsList.add(mMediaItems);

            // MediaItem - Type 2
            mMediaDesBuilder = new MediaDescription.Builder();
            mMediaDesBuilder.setDescription("Group Desc 2");
            mMediaDesBuilder.setTitle("Media Group 2");
            mMediaDesBuilder.setMediaId("MediaGroup2");
            mMediaDesBuilder.setIconBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_round_donut));

            mMediaItems = new MediaBrowser.MediaItem(mMediaDesBuilder.build(), MediaBrowser.MediaItem.FLAG_PLAYABLE);
            mMediaItemsList.add(mMediaItems);


        } else {
            // MediaItem - Type 2
            mMediaDesBuilder = new MediaDescription.Builder();
            mMediaDesBuilder.setDescription("Group Desc 3");
            mMediaDesBuilder.setTitle("Media Group 3");
            mMediaDesBuilder.setMediaId("MediaGroup3");
            mMediaDesBuilder.setIconBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_round_donut));

            mMediaItems = new MediaBrowser.MediaItem(mMediaDesBuilder.build(), MediaBrowser.MediaItem.FLAG_PLAYABLE);
            mMediaItemsList.add(mMediaItems);
        }

        result.sendResult(mMediaItemsList);
    }


    private MediaSession.Callback mCallback = new MediaSession.Callback() {
        @Override
        public void onPlay() {
            super.onPlay();

            Toast.makeText(getApplicationContext(), "Playing", Toast.LENGTH_SHORT).show();

            mPlaybackState = new PlaybackState.Builder()
                    .setActions(PlaybackState.ACTION_PLAY_PAUSE)
                    .setState(PlaybackState.STATE_PLAYING, 0, 1).build();
            mSession.setPlaybackState(mPlaybackState);

        }

        @Override
        public void onPause() {
            super.onPause();

            Toast.makeText(getApplicationContext(), "Paused", Toast.LENGTH_SHORT).show();

            mPlaybackState = new PlaybackState.Builder()
                    .setActions(PlaybackState.ACTION_PLAY_PAUSE)
                    .setState(PlaybackState.STATE_PAUSED, 0, 1).build();
            mSession.setPlaybackState(mPlaybackState);

        }

//        @Override
//        public void onFastForward() {
//            super.onFastForward();
//
//            mPlaybackState = new PlaybackState.Builder()
//                    .setActions(PlaybackState.ACTION_FAST_FORWARD)
//                    .setState(PlaybackState.STATE_FAST_FORWARDING, 0, 1).build();
//            mSession.setPlaybackState(mPlaybackState);
//        }
//
//        @Override
//        public void onSkipToNext() {
//            super.onSkipToNext();
//
//            mPlaybackState = new PlaybackState.Builder()
//                    .setActions(PlaybackState.ACTION_SKIP_TO_NEXT)
//                    .setState(PlaybackState.STATE_SKIPPING_TO_NEXT, 0, 1).build();
//            mSession.setPlaybackState(mPlaybackState);
//
//        }
//
//        @Override
//        public void onSkipToPrevious() {
//            super.onSkipToPrevious();
//            mPlaybackState = new PlaybackState.Builder()
//                    .setActions(PlaybackState.ACTION_SKIP_TO_PREVIOUS)
//                    .setState(PlaybackState.STATE_SKIPPING_TO_PREVIOUS, 0, 1).build();
//            mSession.setPlaybackState(mPlaybackState);
//        }
    };
}