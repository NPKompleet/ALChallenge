package com.example.phenomenon.alchallenge;

/**
 * Created by PHENOMENON on 3/6/2017.
 */

public class Profile {
    public Long mId;
    public String mImgUrl;
    public String mProfileName;
    public String mProfileUrl;

    //Constructor for each profile
    public Profile(Long id, String img, String login, String htmlUrl){
        mId = id;
        mImgUrl = img;
        mProfileName= login;
        mProfileUrl= htmlUrl;
    }

    //a string representation of the class
    public String toString(){
        return "Profile Name: "+ mProfileName;
    }
}
