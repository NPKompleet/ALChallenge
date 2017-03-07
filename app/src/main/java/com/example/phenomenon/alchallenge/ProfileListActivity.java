package com.example.phenomenon.alchallenge;

import android.content.Context;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.phenomenon.alchallenge.dummy.DummyContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        View recyclerView = findViewById(R.id.profile_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.profile_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

 /*   private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
        String url= "https://api.github.com/search/users?q=%22%22+language:java+location:lagos&page=1&per_page=100";
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            //response = response.getJSONObject("args");
                            //String site = response.getString("site")
                            int count= response.getInt("total_count");
                            JSONArray query= response.getJSONArray("items");
                            for (int i=0; i<query.length(); i++) {
                                JSONObject obj= query.getJSONObject(i);
                                Long id= obj.getLong("id");
                                String img= obj.getString("avatar_url");
                                String pName= obj.getString("login");
                                String pUrl=obj.getString("html_url");
                                Profile profile= new Profile(id,img, pName, pUrl);
                                ProfilesCollection.ITEMS.add(profile);
                                ProfilesCollection.ITEM_MAP.put(id, profile);
                            }

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

        recyclerView.setAdapter(new ProfileAdapter(ProfilesCollection.ITEMS));
    }



    public class ProfileAdapter extends RecyclerView.Adapter {

        List<Profile> profiles;

        public class ProfileViewHolder extends RecyclerView.ViewHolder {
            LinearLayout lo;
            ImageView profilePhoto;
            TextView profileName;
            Profile profiles;
            //TextView profileUrl;


            ProfileViewHolder(View itemView) {
                super(itemView);
                lo = (LinearLayout) itemView.findViewById(R.id.profile_list_layout);
                profilePhoto = (ImageView) itemView.findViewById(R.id.image_list);
                profileName = (TextView) itemView.findViewById(R.id.profile_name);
            }
        }

        ProfileAdapter(List<Profile> profiles){
            this.profiles= profiles;
        }


        @Override
        public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_list_content, parent, false);
            ProfileViewHolder profileViewHolder = new ProfileViewHolder(view);
            return profileViewHolder;
        }

        @Override
        public void onBindViewHolder(final ProfileViewHolder holder, int position) {
            //holder.mItem = mValues.get(position);
            holder.profiles=
            //holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ProfileDetailFragment fragment = new ProfileDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.profile_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProfileDetailActivity.class);
                        intent.putExtra(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return profiles.size();
        }
    }
*/

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));

        String url= "https://api.github.com/search/users?q=%22%22+language:java+location:lagos&page=1&per_page=100";
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            //response = response.getJSONObject("args");
                            //String site = response.getString("site")
                            int count= response.getInt("total_count");
                            JSONArray query= response.getJSONArray("items");
                            for (int i=0; i<query.length(); i++) {
                                JSONObject obj= query.getJSONObject(i);
                                Long id= obj.getLong("id");
                                String img= obj.getString("avatar_url");
                                String pName= obj.getString("login");
                                String pUrl=obj.getString("html_url");
                                Profile profile= new Profile(id,img, pName, pUrl);
                                ProfilesCollection.ITEMS.add(profile);
                                ProfilesCollection.ITEM_MAP.put(id, profile);
                            }

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

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(ProfilesCollection.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        //private final List<DummyContent.DummyItem> mValues;
        private final List<Profile> mValues;


        /*public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }*/

        public SimpleItemRecyclerViewAdapter(List<Profile> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            //holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).mProfileName);

            //get image
            String url = mValues.get(position).mImgUrl;
            /*if (url != null){
                ImageLoader imageLoader;
                imageLoader.get(url, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    holder.mImgView.setImageBitmap(imageContainer.getBitmap());

                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e(TAG, "Image Load Error: " + error.getMessage());
                    }
                });

            }*/


            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        //arguments.putString(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        arguments.putLong(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.mId);
                        ProfileDetailFragment fragment = new ProfileDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.profile_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProfileDetailActivity.class);
                        intent.putExtra(ProfileDetailFragment.ARG_ITEM_ID, holder.mItem.mId);

                        context.startActivity(intent);
                    }
                }
            });

            //notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            //public final TextView mIdView;
            //public final TextView mContentView;
            //public DummyContent.DummyItem mItem;
            public final ImageView mImgView;
            public final TextView mContentView;
            public Profile mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //mIdView = (TextView) view.findViewById(R.id.id);
                //mContentView = (TextView) view.findViewById(R.id.content);
                mImgView = (ImageView) view.findViewById(R.id.image_list);
                mContentView = (TextView) view.findViewById(R.id.profile_name);

            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
