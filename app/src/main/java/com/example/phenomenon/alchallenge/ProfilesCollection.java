package com.example.phenomenon.alchallenge;

import android.graphics.Bitmap;

import com.example.phenomenon.alchallenge.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PHENOMENON on 3/6/2017.
 */

public class ProfilesCollection {

    //array to hold profiles
    public static final List<Profile> ITEMS = new ArrayList<Profile>();

    //maps each profile to its id
    public static final Map<Long, Profile> ITEM_MAP = new HashMap<Long, Profile>();

    public static final Map<Long, Bitmap> PIC_MAP = new HashMap<Long, Bitmap>();
}
