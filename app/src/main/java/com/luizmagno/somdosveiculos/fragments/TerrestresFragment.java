package com.luizmagno.somdosveiculos.fragments;

import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.material.snackbar.Snackbar;
import com.luizmagno.somdosveiculos.R;
import java.util.ArrayList;

import static com.luizmagno.somdosveiculos.MainActivity.mp;

public class TerrestresFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Integer> vehicles;

    public TerrestresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_terrestres, container, false);

        //Add Ids
        addIdsOfVehicles();

        //Set Click
        setClickOfVehicles(fragment);

        return fragment;
    }

    private void addIdsOfVehicles() {
        vehicles = new ArrayList<>();
        vehicles.add(R.id.carroId);
        vehicles.add(R.id.bicicletaId);
        vehicles.add(R.id.motoId);
        vehicles.add(R.id.ambuId);
        vehicles.add(R.id.bombId);
        vehicles.add(R.id.lixoId);
        vehicles.add(R.id.policiaId);
        vehicles.add(R.id.tratorId);
        vehicles.add(R.id.tremId);
    }

    public void setClickOfVehicles(View frag) {
        for (int id: vehicles) {
            frag.findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        showAnimation(view);

        try {
            if (mp.isPlaying()) {
                mp.stop();
            }

            mp.reset();

            AssetFileDescriptor afd = null;

            switch (view.getId()) {
                case R.id.carroId:
                    afd = getResources().openRawResourceFd(R.raw.carro_1);
                    showSnackBar(getResources().getString(R.string.carro));
                    break;
                case R.id.bicicletaId:
                    afd = getResources().openRawResourceFd(R.raw.bicicleta_1);
                    showSnackBar(getResources().getString(R.string.bicicleta));
                    break;
                case R.id.caminhaoId:
                    afd = getResources().openRawResourceFd(R.raw.caminhao);
                    showSnackBar(getResources().getString(R.string.caminhao));
                    break;
                case R.id.motoId:
                    afd = getResources().openRawResourceFd(R.raw.moto);
                    showSnackBar(getResources().getString(R.string.moto));
                    break;
                case R.id.ambuId:
                    afd = getResources().openRawResourceFd(R.raw.ambu);
                    showSnackBar(getResources().getString(R.string.ambulancia));
                    break;
                case R.id.bombId:
                    afd = getResources().openRawResourceFd(R.raw.bombeiro);
                    showSnackBar(getResources().getString(R.string.bombeiro));
                    break;
                case R.id.lixoId:
                    afd = getResources().openRawResourceFd(R.raw.lixo_1);
                    showSnackBar(getResources().getString(R.string.lixo));
                    break;
                case R.id.policiaId:
                    afd = getResources().openRawResourceFd(R.raw.policia);
                    showSnackBar(getResources().getString(R.string.policia));
                    break;
                case R.id.tratorId:
                    afd = getResources().openRawResourceFd(R.raw.trator_1);
                    showSnackBar(getResources().getString(R.string.trator));
                    break;
                case R.id.tremId:
                    afd = getResources().openRawResourceFd(R.raw.trem_1);
                    showSnackBar(getResources().getString(R.string.trem));
                    break;
                default:
                    showSnackBar("Error");
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

    private void showAnimation(View view) {
        ImageView imageView = (ImageView) view;
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.stop();
        animationDrawable.start();
    }

    private void showSnackBar(String string) {
        CoordinatorLayout coordinatorLayout = getActivity().findViewById(R.id.coordInMainId);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, string,
                        Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}