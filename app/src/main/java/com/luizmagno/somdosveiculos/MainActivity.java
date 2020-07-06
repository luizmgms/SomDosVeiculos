package com.luizmagno.somdosveiculos;

import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CoordinatorLayout coordinatorLayout;
    private MediaPlayer mp;
    private ArrayList<Integer> veiculos;

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

        //ids dos Veículos
        veiculos = new ArrayList<>();
        addVeiculos();

        //Player
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        //Set click
        for (int id:veiculos) {
            ImageView iV = findViewById(id);
            iV.setOnClickListener(this);
        }

    }

    private void addVeiculos() {
        veiculos.add(R.id.aviaoId);
        veiculos.add(R.id.carroId);
        veiculos.add(R.id.bicicletaId);
        veiculos.add(R.id.caminhaoId);
        veiculos.add(R.id.helicopId);
        veiculos.add(R.id.motoId);
        veiculos.add(R.id.ambuId);
        veiculos.add(R.id.aviaoMonoId);
        veiculos.add(R.id.bombId);
        veiculos.add(R.id.foguetId);
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
            return true;
        } else if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        ImageView imageView = (ImageView) view;
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.stop();
        animationDrawable.start();

        Snackbar snackbar;

        try {
            if (mp.isPlaying()) {
                mp.stop();
            }

            mp.reset();

            AssetFileDescriptor afd = null;

            switch (view.getId()) {
                case R.id.aviaoId:
                    afd = getResources().openRawResourceFd(R.raw.cao);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.aviao), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.carroId:
                    afd = getResources().openRawResourceFd(R.raw.cao);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.carro), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                default:
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Ocorreu um erro!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;

            }



            if (afd != null) {
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepareAsync();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}