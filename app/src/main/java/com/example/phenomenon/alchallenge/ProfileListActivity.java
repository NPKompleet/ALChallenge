package com.example.phenomenon.alchallenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.phenomenon.alchallenge.dummy.DummyContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An activity representing a list of Profiles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProfileDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProfileListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    SimpleItemRecyclerViewAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        View recyclerView = findViewById(R.id.profile_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        //getApiData();
        ProfilesCollection.ITEMS.clear();
        fetchJSON();

        if (findViewById(R.id.profile_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

    }

    private void getApiData(){
        ProfilesCollection.ITEMS.clear();
        //String url= "https://api.github.com/search/users?q=%22%22+language:java+location:lagos&page=1&per_page=100";
        String url= "https://api.github.com/search/users?q=%22%22+language:java+location:lagos";
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            //fetch data
                            int count= response.getInt("total_count");
                            int reqCount=1;
                            if (count > 100){
                                reqCount= (int) Math.ceil(count/100.0);
                            }
                            for (int i=1; i<=reqCount; i++){
                                String link="https://api.github.com/search/users?q=%22%22+language:java+location:lagos&page="
                                        +i+"&per_page=100";

                                //fetchJSON(link);
                            }

                            adp.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);

    }





    private void fetchJSON(){
        String url= "https://api.github.com/search/users?q=%22%22+language:java+location:lagos&page=1&per_page=100";
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            //fetch data
                            int count= response.getInt("total_count");
                            int reqCount;
                            if (count > 100){
                                reqCount= (int) Math.ceil(count/100.0);
                            }
                            JSONArray query= response.getJSONArray("items");
                            for (int i=0; i<query.length(); i++) {
                                JSONObject obj= query.getJSONObject(i);
                                Long id= obj.getLong("id");
                                String img= obj.getString("avatar_url");
                                String pName= obj.getString("login");
                                String pUrl=obj.getString("html_url");
                                //requestImage(id, img);
                                Profile profile= new Profile(id,img, pName, pUrl);
                                ProfilesCollection.ITEMS.add(profile);
                                ProfilesCollection.ITEM_MAP.put(id, profile);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adp.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);


    }



    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        adp= new SimpleItemRecyclerViewAdapter(ProfilesCollection.ITEMS, this);
        recyclerView.setAdapter(adp);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {


        private final List<Profile> profileList;
        private Context context;
        private ImageLoader imageLoader;



        public SimpleItemRecyclerViewAdapter(List<Profile> items, Context context) {
            super();
            profileList = items;
            this.context= context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = profileList.get(position);
            //holder.mIdView.setText(profileList.get(position).id);
            holder.mContentView.setText(profileList.get(position).getmProfileName());

            //Loading image from url
            imageLoader = MyImageRequest.getInstance(context).getImageLoader();
            imageLoader.get(profileList.get(position).getmImgUrl(), ImageLoader.getImageListener(holder.mImgView,
                    R.drawable.ic_person_black_24dp, android.R.drawable.ic_dialog_alert));
            holder.mImgView.setImageUrl(profileList.get(position).getmImgUrl(), imageLoader);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();

                        //puts in the id to be used to create ProfileDetailFragment
                        arguments.putLong(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.getmId());
                        ProfileDetailFragment fragment = new ProfileDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.profile_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProfileDetailActivity.class);
                        intent.putExtra(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.getmId());

                        context.startActivity(intent);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return profileList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;

            public final NetworkImageView mImgView;
            public final TextView mContentView;
            public Profile mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;

                mImgView = (NetworkImageView) view.findViewById(R.id.image_list);
                mContentView = (TextView) view.findViewById(R.id.profile_name);

            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }


}
