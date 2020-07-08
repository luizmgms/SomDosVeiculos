package com.luizmagno.somdosveiculos;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CoordinatorLayout coordinatorLayout;
    private MediaPlayer mp;
    private ArrayList<Integer> vehicles;

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

        //ids of Vehicles
        vehicles = new ArrayList<>();
        addVehicles();

        //Player
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        //Set click
        for (int id: vehicles) {
            ImageView iV = findViewById(id);
            iV.setOnClickListener(this);
        }

    }

    private void addVehicles() {
        vehicles.add(R.id.aviaoId);
        vehicles.add(R.id.carroId);
        vehicles.add(R.id.bicicletaId);
        vehicles.add(R.id.caminhaoId);
        vehicles.add(R.id.helicopId);
        vehicles.add(R.id.motoId);
        vehicles.add(R.id.ambuId);
        vehicles.add(R.id.aviaoMonoId);
        vehicles.add(R.id.bombId);
        vehicles.add(R.id.foguetId);
        vehicles.add(R.id.lixoId);
        vehicles.add(R.id.policiaId);
        vehicles.add(R.id.subId);
        vehicles.add(R.id.tratorId);
        vehicles.add(R.id.tremId);
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

    @Override
    public void onClick(View view) {

        //Animation
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
                    afd = getResources().openRawResourceFd(R.raw.aviao_jato_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.aviao),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                    break;
                case R.id.carroId:
                    afd = getResources().openRawResourceFd(R.raw.carro_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.carro),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.bicicletaId:
                    afd = getResources().openRawResourceFd(R.raw.bicicleta_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.bicicleta),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.caminhaoId:
                    afd = getResources().openRawResourceFd(R.raw.caminhao);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.caminhao),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.helicopId:
                    afd = getResources().openRawResourceFd(R.raw.helicoptero_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.helicop),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.motoId:
                    afd = getResources().openRawResourceFd(R.raw.moto);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.moto),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.ambuId:
                    afd = getResources().openRawResourceFd(R.raw.ambu);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.ambulancia),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.aviaoMonoId:
                    afd = getResources().openRawResourceFd(R.raw.aviao_mono_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.aviao_mono),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.bombId:
                    afd = getResources().openRawResourceFd(R.raw.bombeiro);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.bombeiro),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.foguetId:
                    afd = getResources().openRawResourceFd(R.raw.foguete_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.foguete),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.lixoId:
                    afd = getResources().openRawResourceFd(R.raw.lixo_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.lixo),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.policiaId:
                    afd = getResources().openRawResourceFd(R.raw.policia);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.policia),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.subId:
                    afd = getResources().openRawResourceFd(R.raw.sub_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.sub),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.tratorId:
                    afd = getResources().openRawResourceFd(R.raw.trator_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.trator),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.tremId:
                    afd = getResources().openRawResourceFd(R.raw.trem_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.trem),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                default:
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Error!", Snackbar.LENGTH_SHORT);
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