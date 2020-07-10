package com.luizmagno.somdosveiculos.fragments;

import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.material.snackbar.Snackbar;
import com.luizmagno.somdosveiculos.MainActivity;
import com.luizmagno.somdosveiculos.R;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerrestresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TerrestresFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Integer> vehicles;
    private MediaPlayer mp;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TerrestresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TerrestresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TerrestresFragment newInstance(String param1, String param2) {
        TerrestresFragment fragment = new TerrestresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_terrestres, container, false);

        //Add Ids
        addIdsOfVehicles();

        //Player
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

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
            CoordinatorLayout coordinatorLayout = getActivity().findViewById(R.id.coordInMainId);

            switch (view.getId()) {
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
                case R.id.bombId:
                    afd = getResources().openRawResourceFd(R.raw.bombeiro);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.bombeiro),
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