package com.luizmagno.somdosveiculos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    public static MediaPlayer mp;

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
        mp.release();
        mp = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.coordInMainId);

        Toolbar toolbar = findViewById(R.id.toolbarInMainId);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        toolBarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        setIconsOfTabs(tabs, sectionsPagerAdapter);

        //Inicializando Player
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    private void setIconsOfTabs(TabLayout tabs, final SectionsPagerAdapter sectionsPagerAdapter) {

        for (int i=0; i<sectionsPagerAdapter.getCount(); i++) {
            if (i != 0) {
                tabs.getTabAt(i).setIcon(sectionsPagerAdapter.getDrawable(i));
                tabs.getTabAt(i).getIcon().setAlpha(80);
            } else {
                tabs.getTabAt(i).setIcon(sectionsPagerAdapter.getDrawable(i));
            }
        }

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0) {
                    tab.setIcon(R.drawable.ic_car);
                } else if(position == 1) {
                    tab.setIcon(R.drawable.ic_airplane);
                } else {
                    tab.setIcon(R.drawable.ic_boat);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0) {
                    tab.getIcon().setAlpha(80);
                } else if(position == 1) {
                    tab.getIcon().setAlpha(80);
                } else {
                    tab.getIcon().setAlpha(80);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_share) {
            startShared(getResources().getString(R.string.text_share_link));
            return true;
        } else if (id == R.id.action_about) {
            showDialogAbout();
            return true;
        } else if (id == R.id.action_policy){
            openUrl(getResources().getString(R.string.link_to_policy));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startShared(String str_to_share) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, str_to_share);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void openUrl (String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void showDialogAbout(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View inflate = inflater.inflate(R.layout.layout_about, null);
        builder.setView(inflate);
        builder.create();
        builder.show();

        //Buttons
        Button btnAvaliarApp = inflate.findViewById(R.id.buttonAvalieInAboutId);
        Button btnSharedApp = inflate.findViewById(R.id.buttonShareInAboutId);

        btnSharedApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShared(getResources().getString(R.string.text_share_link));
            }
        });

        btnAvaliarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(getResources().getString(R.string.link_to_avalie));
            }
        });
    }

}