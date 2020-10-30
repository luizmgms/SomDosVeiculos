package com.luizmagno.somdosveiculos;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.luizmagno.somdosveiculos.adapters.AdapterVehicle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdapterVehicle adapterVehicle;
    public CoordinatorLayout coordinatorLayout;


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

        //Lista de ID's de Veículos
        ArrayList<Integer> vehicles = getIdsOfDrawablesVehicles();

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.listViewVehiclesId);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapterVehicle = new AdapterVehicle(vehicles, this);
        recyclerView.setAdapter(adapterVehicle);
        recyclerView.setHasFixedSize(true);

    }

    private ArrayList<Integer> getIdsOfDrawablesVehicles() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.drawable.anim_aviao);
        list.add(R.drawable.anim_carro);
        list.add(R.drawable.anim_bici);
        list.add(R.drawable.anim_caminhao);
        list.add(R.drawable.anim_helicop);
        list.add(R.drawable.anim_moto);
        list.add(R.drawable.anim_ambu);
        list.add(R.drawable.anim_aviao_mono);
        list.add(R.drawable.anim_bombeiro);
        list.add(R.drawable.anim_foguet);
        list.add(R.drawable.anim_lixo);
        list.add(R.drawable.anim_policia);
        list.add(R.drawable.anim_sub);
        list.add(R.drawable.anim_trator);
        list.add(R.drawable.anim_navio);
        list.add(R.drawable.anim_trem);

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
        if(adapterVehicle.mediaPlayer != null) {
            adapterVehicle.mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapterVehicle.mediaPlayer != null) {
            adapterVehicle.mediaPlayer.stop();
            adapterVehicle.mediaPlayer.release();
        }
    }

}