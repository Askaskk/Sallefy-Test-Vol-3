package com.salle.android.sallefy_test_vol_3.controller.restapi.callback;

import com.salle.android.sallefy_test_vol_3.model.Track;

import java.util.List;



public interface TrackCallback extends FailureCallback {
    void onTracksReceived(List<Track> tracks);
    void onNoTracks(Throwable throwable);
    void onPersonalTracksReceived(List<Track> tracks);
    void onUserTracksReceived(List<Track> tracks);
    void onCreateTrack();
}
