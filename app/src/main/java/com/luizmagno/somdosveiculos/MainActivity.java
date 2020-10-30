package com.luizmagno.somdosveiculos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mp;
    private ArrayList<Integer> vehicles;

    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CoorinatorLayout
        coordinatorLayout = findViewById(R.id.coordInMainId);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarInMainId);
        setSupportActionBar(toolbar);

        //CollapsingToolbar
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        //Inicializando Player
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        //Lista de ID's de Veículos
        vehicles = getIdsVehicles();

        //SetClick
        setClickArray(vehicles);

    }

    private void setClickArray(ArrayList<Integer> list) {
        for (int id: list) {
            findViewById(id).setOnClickListener(this);
        }
    }

    private ArrayList<Integer> getIdsVehicles() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.id.aviaoJatoId);
        list.add(R.id.carroId);
        list.add(R.id.bicicletaId);
        list.add(R.id.caminhaoId);
        list.add(R.id.helicopId);
        list.add(R.id.motoId);
        list.add(R.id.ambuId);
        list.add(R.id.aviaoMonoId);
        list.add(R.id.bombId);
        list.add(R.id.foguetId);
        list.add(R.id.lixoId);
        list.add(R.id.policiaId);
        list.add(R.id.subId);
        list.add(R.id.tratorId);
        list.add(R.id.navioId);
        list.add(R.id.tremId);

        return list;
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
    protected void onPause() {
        super.onPause();
        if(mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Inicializando Player
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        playSound(v);
    }

    private void playSound(View v) {
        try {

            //Se estiver tocando, pare
            if (mp.isPlaying()) {
                mp.stop();
            }

            //Reset Player
            mp.reset();

            //Descritor de arquivo
            AssetFileDescriptor afd;

            //Id's id[0] = Nome, id[1] = Raw Som
            int[] id = getIdNameSoundAnimal(v);

            //AssetFileDescriptor recebe Raw
            afd = getResources().openRawResourceFd(id[1]);

            //Set Data Source e PrepareAsync
            if (afd != null) {
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepareAsync();
            }

            //Animação
            startAnimation(v);

            //Show SnackBar
            Snackbar.make(coordinatorLayout, id[0], Snackbar.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(coordinatorLayout, R.string.error, Snackbar.LENGTH_SHORT);
        }
    }

    private void startAnimation(View v) {
        ImageView imageView = (ImageView)v;
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.stop();
        animationDrawable.start();
    }

    @SuppressLint("NonConstantResourceId")
    private int[] getIdNameSoundAnimal (View v) {
        int[] id = {0, 0};

        switch (v.getId()) {
            case R.id.aviaoJatoId:
                id[0] = R.string.aviao_jato;
                id[1] = R.raw.aviao_jato_1;
                break;
            case R.id.carroId:
                id[0] = R.string.carro;
                id[1] = R.raw.carro_1;
                break;
            case R.id.bicicletaId:
                id[0] = R.string.bicicleta;
                id[1] = R.raw.bicicleta_1;
                break;
            case R.id.caminhaoId:
                id[0] = R.string.caminhao;
                id[1] = R.raw.caminhao;
                break;
            case R.id.helicopId:
                id[0] = R.string.helicop;
                id[1] = R.raw.helicoptero_1;
                break;
            case R.id.motoId:
                id[0] = R.string.moto;
                id[1] = R.raw.moto;
                break;
            case R.id.ambuId:
                id[0] = R.string.ambulancia;
                id[1] = R.raw.ambu;
                break;
            case R.id.aviaoMonoId:
                id[0] = R.string.aviao_mono;
                id[1] = R.raw.aviao_mono_1;
                break;
            case R.id.bombId:
                id[0] = R.string.bombeiro;
                id[1] = R.raw.bombeiro;
                break;
            case R.id.foguetId:
                id[0] = R.string.foguete;
                id[1] = R.raw.foguete_1;
                break;
            case R.id.lixoId:
                id[0] = R.string.lixo;
                id[1] = R.raw.lixo_1;
                break;
            case R.id.policiaId:
                id[0] = R.string.policia;
                id[1] = R.raw.policia;
                break;
            case R.id.subId:
                id[0] = R.string.sub;
                id[1] = R.raw.sub_1;
                break;
            case R.id.tratorId:
                id[0] = R.string.trator;
                id[1] = R.raw.trator_1;
                break;
            case R.id.navioId:
                id[0] = R.string.navio;
                id[1] = R.raw.navio;
                break;
            case R.id.tremId:
                id[0] = R.string.trem;
                id[1] = R.raw.trem_1;
                break;
            default:
                id[0] = 0;
                id[1] = R.string.error;
                break;
        }

        return id;
    }
}