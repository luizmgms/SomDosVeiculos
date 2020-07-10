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
import com.luizmagno.somdosveiculos.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AquaticosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AquaticosFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Integer> vehicles;
    private MediaPlayer mp;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AquaticosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AquaticosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AquaticosFragment newInstance(String param1, String param2) {
        AquaticosFragment fragment = new AquaticosFragment();
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
        View fragment =  inflater.inflate(R.layout.fragment_aquaticos, container, false);

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
        vehicles.add(R.id.subId);
        vehicles.add(R.id.navioId);
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
                case R.id.subId:
                    afd = getResources().openRawResourceFd(R.raw.sub_1);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.sub),
                                    Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                case R.id.navioId:
                    afd = getResources().openRawResourceFd(R.raw.navio);
                    snackbar = Snackbar
                            .make(coordinatorLayout, getResources().getString(R.string.navio),
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