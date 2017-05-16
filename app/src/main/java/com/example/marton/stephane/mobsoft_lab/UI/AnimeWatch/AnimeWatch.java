package com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.R;
import com.example.marton.stephane.mobsoft_lab.UI.Options.Options;
import com.example.marton.stephane.mobsoft_lab.adapters.CommentAdapter;
import com.example.marton.stephane.mobsoft_lab.adapters.SimilarAnimeAdapter;
import com.example.marton.stephane.mobsoft_lab.models.Anime;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.SimilarAnime;
import com.example.marton.stephane.mobsoft_lab.utils.CacheSystem;
import com.example.marton.stephane.mobsoft_lab.utils.Connectivity;
import com.example.marton.stephane.mobsoft_lab.utils.DatabaseController;
import com.example.marton.stephane.mobsoft_lab.utils.MyListView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

public class AnimeWatch extends AppCompatActivity implements AnimeWatchScreen {
    TextView title, eps, dates, type, description, ratingTemp, ratingPerm, similarText;
    ImageView cover;
    MyListView similarList, commentsList;
    SimilarAnimeAdapter similarAdapter;
    CommentAdapter commentAdapter;
    Button openInBrowser, saveComment;
    Uri imageUri;
    EditText newCommentText;
    private String PrefFileName;

    @Inject
    AnimeWatchPresenter animeWatchPresenter;

    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_watch);


        cover = (ImageView) this.findViewById(R.id.cover);
        title = (TextView) this.findViewById(R.id.title);
        eps = (TextView) this.findViewById(R.id.eps);
        dates = (TextView) this.findViewById(R.id.dates);
        type = (TextView) this.findViewById(R.id.type);
        description = (TextView) this.findViewById(R.id.description);
        ratingPerm = (TextView) this.findViewById(R.id.ratingPerm);
        ratingTemp = (TextView) this.findViewById(R.id.ratingTemp);
        similarList = (MyListView) this.findViewById(R.id.similarList);
        commentsList = (MyListView) this.findViewById(R.id.commentsList);
        similarText = (TextView) this.findViewById(R.id.similarText);
        openInBrowser = (Button) this.findViewById(R.id.openInBrowser);
        saveComment = (Button) this.findViewById(R.id.saveComment);
        newCommentText = (EditText) this.findViewById(R.id.newCommentText);

        // Obtain the shared Tracker instance.
        MobSoftApplication application = (MobSoftApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Fabric.with(this, new Crashlytics());

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("Image~AnimeWatch");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        animeWatchPresenter.attachScreen(this);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("animeId");

        if (id.isEmpty()) {
            Toast.makeText(AnimeWatch.this, R.string.error_id_missing, Toast.LENGTH_LONG).show();
        }
        else {
            openInBrowser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://anidb.net/perl-bin/animedb.pl?show=anime&aid=" + id));
                    startActivity(browserIntent);
                }
            });
            animeWatchPresenter.StartDownload(id, this);
            animeWatchPresenter.getComments(id);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        animeWatchPresenter.detachScreen();
    }


    @Override
    public void showMessage(String text) {
        if (!isFinishing()) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }


    public void onCacheSuccess(final Object object) {
        if (!isFinishing()) {
            if (object instanceof Anime) {
                Anime a = (Anime) object;
                imageUri = a.getPicURI();
                cover.setImageURI(imageUri);
                title.setText(a.getTitle());
                eps.setText(getString(R.string.episodes) + a.getFullEps());
                dates.setText(a.getDates());
                type.setText(a.getType());
                description.setText(a.getDescription());
                ratingPerm.setText(getString(R.string.permanent_rating) + a.getFullRatingPerm());
                ratingTemp.setText(getString(R.string.temporary_rating) + a.getFullRatingTemp());

                ArrayList<SimilarAnime> sa = new ArrayList<SimilarAnime>(Arrays.asList(a.getSimilarAnimes()));

                if (!sa.isEmpty()) {
                    similarText.setText(R.string.similar_anime);
                }

                similarAdapter = new SimilarAnimeAdapter(sa);

                similarList.setAdapter(similarAdapter);
                similarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SimilarAnime item = (SimilarAnime) similarAdapter.getItem(position);

                        Intent intent = new Intent(AnimeWatch.this, AnimeWatch.class);
                        intent.putExtra("animeId", item.getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public void onCacheImageReady(final String id) {
        if (!isFinishing()) {
            cover.setImageURI(null);
            cover.setImageURI(imageUri);
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
            Intent intent = new Intent(AnimeWatch.this, Options.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void receiveComments(ArrayList<Comment> comments) {
        if (!isFinishing()) {
            commentAdapter = new CommentAdapter(comments);

            commentsList.setAdapter(commentAdapter);
        }
    }
}
