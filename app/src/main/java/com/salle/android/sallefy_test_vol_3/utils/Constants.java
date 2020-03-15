package com.salle.android.sallefy_test_vol_3.utils;

public class Constants {
    public static final String URL = "url";

    public interface CALLBACKS {
        public static int DELETE_SONG_FROM_PLAYLIST = 1;
    }

    public interface NETWORK {
        public static  String BASE_URL = "http://" + "sallefy.eu-west-3.elasticbeanstalk.com/api/";
        public static int LOGIN_OK = 1;
        public static int LOGIN_KO = 2;
    }

    public interface SENDING {
        public static String PLAYLIST = "sendingPlaylist";
        public static String INDEX = "sendingIndex";
        public static String TRACK = "sendingTrack";
        public static String TRACKS = "sendingTracks";
    }

    public interface ACTION {
        public static String STARTFOREGROUND_ACTION = "salle.android.projects.sallefy_heroes_v10.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "salle.android.projects.sallefy_heroes_v10.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
