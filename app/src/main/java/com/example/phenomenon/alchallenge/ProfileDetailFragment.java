package com.example.phenomenon.alchallenge;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


/**
 * A fragment representing a single Profile detail screen.
 * This fragment is either contained in a {@link ProfileListActivity}
 * in two-pane mode (on tablets) or a {@link ProfileDetailActivity}
 * on handsets.
 */
public class ProfileDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Profile profile;
    private ImageLoader imageLoader;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfileDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load profile from the fragment arguments
            profile = ProfilesCollection.ITEM_MAP.get(getArguments().getLong(ARG_ITEM_ID));



            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {

                appBarLayout.setTitle("Profile");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_detail, container, false);

        // Show profile picture, profile name and github link
        if (profile != null) {
            ProfileDetailActivity.profile= profile;
            NetworkImageView imageView= (NetworkImageView) rootView.findViewById(R.id.detailimage);
            ((TextView) rootView.findViewById(R.id.detailname)).setText(profile.getmProfileName());
            ((TextView) rootView.findViewById(R.id.detaillink)).setText(profile.getmProfileUrl());

            //load the profile picture
            imageLoader = MyImageRequest.getInstance(this.getActivity()).getImageLoader();
            imageLoader.get(profile.getmImgUrl(), ImageLoader.getImageListener(imageView,
                    R.drawable.ic_person_black_24dp, android.R.drawable.ic_dialog_alert));
            imageView.setImageUrl(profile.getmImgUrl(), imageLoader);
        }

        return rootView;
    }
}
