package com.example.marton.stephane.mobsoft_lab.UI.Options;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.example.marton.stephane.mobsoft_lab.R;
import com.example.marton.stephane.mobsoft_lab.UI.Main.MainActivity;
import com.example.marton.stephane.mobsoft_lab.UI.Presenter;
import com.example.marton.stephane.mobsoft_lab.interactor.login.LoginInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.login.events.LoginEvent;
import com.example.marton.stephane.mobsoft_lab.models.Profile;

import java.io.File;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Marton on 2017.03.27..
 */

public class OptionsPresenter extends Presenter<OptionsScreen> {

    @Inject
    LoginInteractor loginInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;


    public OptionsPresenter() {
    }

    @Override
    public void attachScreen(OptionsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }


    public void deleteCache(Options options) {
        File dir = new File(Environment.getExternalStorageDirectory()+"/AniDBApp");

        options.showMessage(options.getResources().getString(R.string.wait_until_notification));

        DeleteRecursive(dir);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(options)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(options.getString(R.string.notification_cache_clear))
                        .setContentText(options.getString(R.string.notification_cache_clear_desc));
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(options, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(options);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) options.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    void DeleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                DeleteRecursive(child);

        fileOrDirectory.delete();
    }

    public void Login(final String username, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginInteractor.Login(username, password);
            }
        });
    }

    public void onEventMainThread(LoginEvent event) {
        Log.d("test","test");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                Profile profile = event.getProfile();
                if (profile != null) {
                    screen.showMessage("Ok!");
                }
                else {
                    screen.showMessage("Wrong user/pass!");
                }
            }
        }
    }

}
