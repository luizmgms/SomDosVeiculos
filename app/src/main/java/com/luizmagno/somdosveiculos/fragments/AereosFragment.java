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

public class AereosFragment extends Fragment implements View.OnClickListener{

    private ArrayList<Integer> vehicles;

    public AereosFragment() {
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
        View fragment = inflater.inflate(R.layout.fragment_aereos, container, false);

        //Add Ids
        addIdsOfVehicles();

        //Set Click
        setClickOfVehicles(fragment);

        return fragment;
    }

    private void addIdsOfVehicles() {
        vehicles = new ArrayList<>();
        vehicles.add(R.id.aviaoId);
        vehicles.add(R.id.helicopId);
        vehicles.add(R.id.aviaoMonoId);
        vehicles.add(R.id.foguetId);
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
                case R.id.aviaoId:
                    afd = getResources().openRawResourceFd(R.raw.aviao_jato_1);
                    showSnackBar(getResources().getString(R.string.aviao));
                    break;
                case R.id.aviaoMonoId:
                    afd = getResources().openRawResourceFd(R.raw.aviao_mono_1);
                    showSnackBar(getResources().getString(R.string.aviao_mono));
                    break;
                case R.id.helicopId:
                    afd = getResources().openRawResourceFd(R.raw.helicoptero_1);
                    showSnackBar(getResources().getString(R.string.helicop));
                    break;
                case R.id.foguetId:
                    afd = getResources().openRawResourceFd(R.raw.foguete_1);
                    showSnackBar(getResources().getString(R.string.foguete));
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