package com.udylity.sandbarfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class SandbarFragment extends Fragment implements RatingBar.OnRatingBarChangeListener {

    private RatingBar mRatingBar;
    private TextView mTitleTextView;
    private int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sandbar, container, false);
        mRatingBar = (RatingBar)rootView.findViewById(R.id.ratingBar);
        mTitleTextView = (TextView)rootView.findViewById(R.id.titleTextView);


        mRatingBar.setOnRatingBarChangeListener(this);
        mRatingBar.setRating(Sandbar.mSandbar[index].getRate());

        mTitleTextView.setText(Sandbar.mSandbar[index].getTitle());
        return rootView;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        switch (ratingBar.getId()){
            case R.id.ratingBar:
                Sandbar.mSandbar[index].setRate(rating);
                break;
        }
    }
}
