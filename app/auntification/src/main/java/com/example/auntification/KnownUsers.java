package com.example.auntification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class KnownUsers {
    private static ArrayList<String> hashes = new ArrayList<String>();

    private static void onCreate(){
        hashes.add("d2abaa37a7c3db1137d385e1d8c15fd2"); // admin, admin
        hashes.add("22ce1a35b913d3840f9b72fe0f87943e"); // m_9sco, q1w23
        hashes.add("90db0030e7a6df3b0f4cba5512f72521"); // user, 123
    }

    public static void addHashes(String hash) {
        if (hashes.isEmpty()){
            onCreate();
        }
        hashes.add(hash);
    }

    public static ArrayList<String> getHashes() {
        if (hashes.isEmpty()){
            onCreate();
        }

        return hashes;
    }
}
