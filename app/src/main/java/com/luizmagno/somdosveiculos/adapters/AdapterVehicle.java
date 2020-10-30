package com.luizmagno.somdosveiculos.adapters;

import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.luizmagno.somdosveiculos.MainActivity;
import com.luizmagno.somdosveiculos.R;

import java.util.ArrayList;

public class AdapterVehicle extends RecyclerView.Adapter<AdapterVehicle.ItemVehicleViewHolder> {


    private final ArrayList<Integer> listVehicles;
    private final MainActivity mainActivity;
    public final MediaPlayer mediaPlayer;

    //INNER CLASS
    public static class ItemVehicleViewHolder extends RecyclerView.ViewHolder {

        ImageView imageVehicle;

        public ItemVehicleViewHolder(View view) {
            super(view);

            imageVehicle = view.findViewById(R.id.imageViewVehicleId);

        }
    }

    public AdapterVehicle(ArrayList<Integer> list, MainActivity mActivity) {
        this.listVehicles = list;
        this.mainActivity = mActivity;
        this.mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @NonNull
    @Override
    public ItemVehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_vehicles, parent, false);

        return new ItemVehicleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVehicleViewHolder holder, int position) {

        final int id = listVehicles.get(position);
        holder.imageVehicle.setImageResource(id);
        setClick(holder.imageVehicle, position);

    }

    @Override
    public int getItemCount() {
        return listVehicles.size();
    }

    private void setClick(ImageView imageView, final int pos) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(pos);
                startAnimation(v);
            }
        });
    }

    private void playSound(int p) {

        try {

            //Se estiver tocando, pare
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            //Reset Player
            mediaPlayer.reset();

            //Descritor de arquivo
            AssetFileDescriptor afd;

            //Id's id[0] = Nome, id[1] = Raw Som
            int[] id = getIdNameSoundVehicle(p);

            //AssetFileDescriptor recebe Raw
            afd = mainActivity.getResources().openRawResourceFd(id[1]);

            //Set Data Source e PrepareAsync
            if (afd != null) {
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayer.prepareAsync();
            }

            //Show SnackBar
            Snackbar.make(mainActivity.coordinatorLayout, id[0], Snackbar.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(mainActivity.coordinatorLayout, R.string.error, Snackbar.LENGTH_SHORT);
        }
    }

    private int[] getIdNameSoundVehicle(int p) {
        int[] id = {0, 0};

        switch (p) {
            case 0:
                id[0] = R.string.aviao_jato;
                id[1] = R.raw.aviao_jato_1;
                break;
            case 1:
                id[0] = R.string.carro;
                id[1] = R.raw.carro_1;
                break;
            case 2:
                id[0] = R.string.bicicleta;
                id[1] = R.raw.bicicleta_1;
                break;
            case 3:
                id[0] = R.string.caminhao;
                id[1] = R.raw.caminhao;
                break;
            case 4:
                id[0] = R.string.helicop;
                id[1] = R.raw.helicoptero_1;
                break;
            case 5:
                id[0] = R.string.moto;
                id[1] = R.raw.moto;
                break;
            case 6:
                id[0] = R.string.ambulancia;
                id[1] = R.raw.ambu;
                break;
            case 7:
                id[0] = R.string.aviao_mono;
                id[1] = R.raw.aviao_mono_1;
                break;
            case 8:
                id[0] = R.string.bombeiro;
                id[1] = R.raw.bombeiro;
                break;
            case 9:
                id[0] = R.string.foguete;
                id[1] = R.raw.foguete_1;
                break;
            case 10:
                id[0] = R.string.lixo;
                id[1] = R.raw.lixo_1;
                break;
            case 11:
                id[0] = R.string.policia;
                id[1] = R.raw.policia;
                break;
            case 12:
                id[0] = R.string.sub;
                id[1] = R.raw.sub_1;
                break;
            case 13:
                id[0] = R.string.trator;
                id[1] = R.raw.trator_1;
                break;
            case 14:
                id[0] = R.string.navio;
                id[1] = R.raw.navio;
                break;
            case 15:
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

    private void startAnimation(View v) {
        ImageView imageView = (ImageView)v;
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.stop();
        animationDrawable.start();
    }
}
