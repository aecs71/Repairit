package com.spotify.repairit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

/**
 * Created by anurag-local on 05-Mar-17.
 */


public class LandingFragment extends android.support.v4.app.Fragment {
    Toolbar toolbar;
    Button scheduleRepairBtn;
    ViewFlipper viewFlipper;
    private static final int Flip_Duration=2000;
    private static final String TAG= LandingFragment.class.getSimpleName();
    public LandingFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView:ffffffffffffff " );
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);

        scheduleRepairBtn= (Button) rootView.findViewById(R.id.scheduleaRepair);
        scheduleRepairBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick:gggggggggggggggggggg " );
                Intent intent= new Intent(getActivity(),ContactActivity.class);
                LandingFragment.this.startActivity(intent);
            }
        });
        viewFlipper=(ViewFlipper) rootView.findViewById(R.id.imageFlipper);
        startSlideShow();
        return rootView;
    }

    private void startSlideShow() {
        if (!viewFlipper.isFlipping()) {
            viewFlipper.setAutoStart(true);
            viewFlipper.setFlipInterval(Flip_Duration);
            viewFlipper.startFlipping();
        }
    }



}

