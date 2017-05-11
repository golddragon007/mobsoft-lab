package com.example.marton.stephane.mobsoft_lab.UI.Options;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.R;
import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatchPresenter;
import com.example.marton.stephane.mobsoft_lab.UI.Main.MainActivity;

import java.io.File;

import javax.inject.Inject;

public class Options extends AppCompatActivity implements OptionsScreen {

    CheckBox onlyWifi;
    String PrefFileName;
    Button backButton, cacheDelete, testLoginButton;
    EditText username, password;

    @Inject
    OptionsPresenter optionsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        MobSoftApplication.injector.inject(this);


        PrefFileName = "AniDBAppPrefs";

        onlyWifi = (CheckBox) findViewById(R.id.only_wifi);
        backButton = (Button) findViewById(R.id.back);
        cacheDelete = (Button) findViewById(R.id.cache_delete);
        testLoginButton = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);

        final Options options = this;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cacheDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Options.this);
                alertDialogBuilder.setTitle(getString(R.string.dialog_delete_cache));
                alertDialogBuilder
                        .setMessage(getString(R.string.dialog_delete_cache_message))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                optionsPresenter.deleteCache(options);
                            }
                        })
                        .setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        testLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        optionsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        optionsPresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PrefFileName, 0);
        Boolean onlyWifiPref = settings.getBoolean("onlyWifi", false);

        onlyWifi.setChecked(onlyWifiPref);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(PrefFileName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("onlyWifi", onlyWifi.isChecked());
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
