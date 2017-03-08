package com.example.phenomenon.alchallenge;

/**
 * Created by PHENOMENON on 3/6/2017.
 */

public class Profile {
    private Long mId;
    private String mImgUrl;
    private String mProfileName;
    private String mProfileUrl;

    //Constructor for each profile
    public Profile(Long id, String img, String login, String htmlUrl){
        mId = id;
        mImgUrl = img;
        mProfileName= login;
        mProfileUrl= htmlUrl;
    }

    public Long getmId() {
        return mId;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public String getmProfileName() {
        return mProfileName;
    }

    public String getmProfileUrl() {
        return mProfileUrl;
    }

    //a string representation of the class
    public String toString(){
        return "Profile Name: "+ mProfileName;
    }
}
