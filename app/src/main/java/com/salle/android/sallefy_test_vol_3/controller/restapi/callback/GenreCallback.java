package com.salle.android.sallefy_test_vol_3.controller.restapi.callback;

import com.salle.android.sallefy_test_vol_3.model.Genre;
import com.salle.android.sallefy_test_vol_3.model.Track;

import java.util.ArrayList;

public interface GenreCallback extends FailureCallback {

    void onGenresReceive(ArrayList<Genre> genres);
    void onTracksByGenre(ArrayList<Track> tracks);
}
