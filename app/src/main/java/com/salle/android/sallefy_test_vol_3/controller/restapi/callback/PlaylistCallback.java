package com.salle.android.sallefy_test_vol_3.controller.restapi.callback;

import com.salle.android.sallefy_test_vol_3.model.Playlist;

import java.util.ArrayList;

public interface PlaylistCallback extends FailureCallback {

    void onPlaylistById(Playlist playlist);

    void onPlaylistsByUser(ArrayList<Playlist> playlists);

    void onAllList(ArrayList<Playlist> playlists);

    void onFollowingList(ArrayList<Playlist> playlists);

}
