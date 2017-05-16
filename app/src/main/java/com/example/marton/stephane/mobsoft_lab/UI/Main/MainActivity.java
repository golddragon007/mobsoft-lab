package com.example.marton.stephane.mobsoft_lab.UI.Main;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.R;
import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatch;
import com.example.marton.stephane.mobsoft_lab.UI.Options.Options;
import com.example.marton.stephane.mobsoft_lab.adapters.AnimeListItemAdapter;
import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.utils.BackgroundService;
import com.example.marton.stephane.mobsoft_lab.utils.CacheSystem;
import com.example.marton.stephane.mobsoft_lab.utils.Connectivity;
import com.example.marton.stephane.mobsoft_lab.utils.DatabaseController;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements MainScreen {

    private ListView hot;
    AnimeListItemAdapter adapter;
    int permission;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Inject
    MainPresenter mainPresenter;

    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public void verifyStoragePermissions(final Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            new AlertDialog.Builder(this)
                    .setTitle(R.string.storage_permission)
                    .setMessage(R.string.storage_permission_need)
                    .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(activity,
                                    PERMISSIONS_STORAGE,
                                    REQUEST_EXTERNAL_STORAGE);
                        }
                    })
                    .create()
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the shared Tracker instance.
        MobSoftApplication application = (MobSoftApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Fabric.with(this, new Crashlytics());

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("Image~MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);

        if (android.os.Build.VERSION.SDK_INT > 22) {
            verifyStoragePermissions(this);
            permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (android.os.Build.VERSION.SDK_INT <= 22 || permission == PackageManager.PERMISSION_GRANTED) {
            startService(new Intent(this, BackgroundService.class));

            mainPresenter.StartDownload(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        if (!isFinishing()) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    public void onCacheSuccess(final Object obj) {
        if (!isFinishing()) {
            if (obj instanceof ArrayList<?> && ((ArrayList<?>)obj).get(0) instanceof AnimeListItem) {
                adapter = new AnimeListItemAdapter((ArrayList<AnimeListItem>) obj, MainActivity.this);

                hot = (ListView) findViewById(R.id.hot);
                hot.setAdapter(adapter);
                hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AnimeListItem item = (AnimeListItem) adapter.getItem(position);

                        Intent intent = new Intent(MainActivity.this, AnimeWatch.class);
                        intent.putExtra("animeId", item.getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public void onCacheImageReady(String id) {
        if (!isFinishing()) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, Options.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
