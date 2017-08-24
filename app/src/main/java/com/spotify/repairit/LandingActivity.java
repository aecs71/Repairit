package com.spotify.repairit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.view.animation.Animation;

import com.spotify.repairit.fragments.TwoFragment;

/**
 * Created by aecs7 on 26-Feb-17.
 */

public class LandingActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE =1234;
    Toolbar toolbar;
    Button scheduleRepairBtn;
    ViewFlipper viewFlipper;
    ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final int Flip_Duration = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
       setContentView(R.layout.activity_landing);
      //  toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("RepairIt");
      //  setSupportActionBar(toolbar);
        //toolbar.setLogo(R.drawable.ic_build_white_24dp);
        // toolbar.setTitleMarginStart(50);


        // viewFlipper=(ViewFlipper) findViewById(R.id.imageFlipper);
        //viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.leftslide));
        //viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_out));
        // startSlideShow();
        // animateSlideShow();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }



    private void startSlideShow() {
        if (!viewFlipper.isFlipping()) {
            viewFlipper.setAutoStart(true);
            viewFlipper.setFlipInterval(Flip_Duration);
            viewFlipper.startFlipping();
        }
    }

    private void animateSlideShow() {
        viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });
    }

   /* public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
            // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format));
            return rootView;
        }
    }*/


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new LandingFragment();
                case 1:
                    return new TwoFragment();

            }
            return null;

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "How it Works?";

            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(LandingActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                }
                else
                executeCall(item);
        return false;

            }



    private void executeCall(MenuItem item) {
        try {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.callAction:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123456789"));
                this.startActivity(callIntent);
                break;
        }

    }
        catch (SecurityException se)
        {
            Toast.makeText(this,se.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) throws SecurityException {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:123456789"));
                    this.startActivity(callIntent);



                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}
