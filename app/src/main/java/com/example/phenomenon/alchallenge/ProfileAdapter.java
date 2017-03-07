package com.example.phenomenon.alchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by PHENOMENON on 3/6/2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter {

    List<Profile> profiles;

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lo;
        ImageView profilePhoto;
        TextView profileName;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_list_content, parent, false);
        ProfileViewHolder profileViewHolder = new ProfileViewHolder(view);
        return profileViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
