package com.example.latte.kakao_demo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.latte.kakao_demo.log.Logger;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {
    private static volatile GlobalApplication instance = null;
    private ImageLoader imageLoader;

    // get singleton application object
    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    // reset image loader, image cache, request queue
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());
//        PushService.init();

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            final LruCache<String, Bitmap> imageCache = new LruCache<>(30);

            @Override
            public void putBitmap(String key, Bitmap value) {
                imageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return imageCache.get(key);
            }
        };

        imageLoader = new ImageLoader(requestQueue, imageCache);

        createNotificationChannel();
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    // when application is terminate, reset singleten application object
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    // separate notification for API level above or equal to 26
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm == null) {
                Logger.e("Failed to fetch NotificationManager from context.");
                return;
            }
            String channelId = "kakao_push_channel";
            String channelName = "Kakao SDK Push";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            nm.createNotificationChannel(channel);
            Logger.d("successfully created a notification channel.");
        }
    }
}
