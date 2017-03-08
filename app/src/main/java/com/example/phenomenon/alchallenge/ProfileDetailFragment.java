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
import com.example.phenomenon.alchallenge.dummy.DummyContent;

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

    /**
     * The dummy content this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;
    private Profile mItem;
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
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            mItem = ProfilesCollection.ITEM_MAP.get(getArguments().getLong(ARG_ITEM_ID));

            //int num= ProfilesCollection.ITEM_MAP.size();

            Activity activity = this.getActivity();
            Toast.makeText(activity, "Working for now "+ getArguments().getLong(ARG_ITEM_ID), Toast.LENGTH_SHORT).show();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                //appBarLayout.setTitle(mItem.content);
                appBarLayout.setTitle("Profile");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ProfileDetailActivity.profile= mItem;
            NetworkImageView imageView= (NetworkImageView) rootView.findViewById(R.id.detailimage);
            ((TextView) rootView.findViewById(R.id.detailname)).setText(mItem.mProfileName);
            ((TextView) rootView.findViewById(R.id.detaillink)).setText(mItem.mProfileUrl);

            imageLoader = MyImageRequest.getInstance(this.getActivity()).getImageLoader();
            imageLoader.get(mItem.mImgUrl, ImageLoader.getImageListener(imageView,
                    R.drawable.ic_person_black_24dp, android.R.drawable.ic_dialog_alert));

            imageView.setImageUrl(mItem.mImgUrl, imageLoader);
        }

        return rootView;
    }
}
