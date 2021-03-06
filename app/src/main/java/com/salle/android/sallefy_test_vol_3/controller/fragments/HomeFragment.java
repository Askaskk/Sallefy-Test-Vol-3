package com.salle.android.sallefy_test_vol_3.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.salle.android.sallefy_test_vol_3.R;
import com.salle.android.sallefy_test_vol_3.controller.adapters.PlaylistListAdapter;
import com.salle.android.sallefy_test_vol_3.controller.callbacks.PlaylistAdapterCallback;
import com.salle.android.sallefy_test_vol_3.controller.restapi.callback.PlaylistCallback;
import com.salle.android.sallefy_test_vol_3.controller.restapi.manager.PlaylistManager;
import com.salle.android.sallefy_test_vol_3.model.Playlist;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements PlaylistAdapterCallback, PlaylistCallback {

    public static final String TAG = HomeFragment.class.getName();
    private RecyclerView mRecyclerView;
    private PlaylistListAdapter mAdapter;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void initViews(View v) {
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mAdapter = new PlaylistListAdapter(null, getContext(), this, R.layout.item_playlist);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.home_recyclerview);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getData() {
        PlaylistManager.getInstance(getContext())
                .getListOfPlaylist(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onPlaylistClick(Playlist playlist) {

    }

    /**********************************************************************************************
     *   *   *   *   *   *   *   *   PlaylistCallback   *   *   *   *   *   *   *   *   *
     **********************************************************************************************/
    @Override
    public void onPlaylistById(Playlist playlist) {

    }

    @Override
    public void onPlaylistsByUser(ArrayList<Playlist> playlists) {

    }

    @Override
    public void onAllList(ArrayList<Playlist> playlists) {
        mAdapter = new PlaylistListAdapter(playlists, getContext(), this, R.layout.item_playlist);
        mRecyclerView.setAdapter(mAdapter);
        //Toast.makeText(getContext(), "Playlists received", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFollowingList(ArrayList<Playlist> playlists) {

    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
    }
}
