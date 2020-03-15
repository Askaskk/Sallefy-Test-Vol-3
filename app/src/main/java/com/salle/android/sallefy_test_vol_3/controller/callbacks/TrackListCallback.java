package com.salle.android.sallefy_test_vol_3.controller.callbacks;

import com.salle.android.sallefy_test_vol_3.model.Track;

public interface TrackListCallback {
    void onTrackSelected(Track track);
    void onTrackSelected(int index);
}
