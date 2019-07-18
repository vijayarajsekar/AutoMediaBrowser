package com.med.simu.Services;

import android.annotation.TargetApi;
import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Bundle;
import android.service.media.MediaBrowserService;

import androidx.annotation.RequiresApi;

import java.util.List;


public class MediaBroService extends MediaBrowserService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public BrowserRoot onGetRoot(String s, int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadChildren(String s, Result<List<MediaBrowser.MediaItem>> result) {

    }
}
