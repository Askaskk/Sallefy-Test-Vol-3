package com.salle.android.sallefy_test_vol_3.controller.restapi.manager;

import android.content.Context;
import android.util.Log;

import com.salle.android.sallefy_test_vol_3.controller.restapi.callback.PlaylistCallback;
import com.salle.android.sallefy_test_vol_3.controller.restapi.service.PlaylistService;
import com.salle.android.sallefy_test_vol_3.model.Playlist;
import com.salle.android.sallefy_test_vol_3.model.UserToken;
import com.salle.android.sallefy_test_vol_3.utils.Constants;
import com.salle.android.sallefy_test_vol_3.utils.Session;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaylistManager {

    private static final String TAG = "PlaylistManager";

    private static PlaylistManager sUserManager;
    private Retrofit mRetrofit;
    private Context mContext;

    private PlaylistService mService;
    private UserToken userToken;


    public static PlaylistManager getInstance(Context context) {
        if (sUserManager == null) {
            sUserManager = new PlaylistManager(context);
        }
        return sUserManager;
    }

    private PlaylistManager(Context cntxt) {
        mContext = cntxt;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.NETWORK.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = mRetrofit.create(PlaylistService.class);
        this.userToken = Session.getInstance(mContext).getUserToken();

    }

    public synchronized void getPlaylistById(Integer id, final PlaylistCallback playlistCallback) {
        Call<Playlist> call = mService.getPlaylistById(id, "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                int code = response.code();
                if (response.isSuccessful()) {
                    playlistCallback.onPlaylistById(response.body());
                } else {
                    Log.d(TAG, "Error Not Successful: " + code);
                    playlistCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable t) {
                Log.d(TAG, "Error Failure: " + t.getStackTrace());
                playlistCallback.onFailure(new Throwable("ERROR " + t.getStackTrace()));
            }
        });
    }

    /**********************
     * Get all playlist of backend
     **********************/
    public synchronized void getListOfPlaylist (final PlaylistCallback playlistCallback) {

        Call<List<Playlist>> call = mService.getAllPlaylists("Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {

                int code = response.code();
                ArrayList<Playlist> data = (ArrayList<Playlist>) response.body();

                if (response.isSuccessful()) {
                    playlistCallback.onAllList(data);
                    Log.d(TAG, "getList");

                } else {
                    Log.d(TAG, "Error: " + code);
                    playlistCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d(TAG, "Error: " + t.getMessage());
                playlistCallback.onFailure(t);
            }
        });
    }

    /**********************
     * Get playlists by user
     **********************/
    public synchronized void getPlaylistsByUser(String login, final PlaylistCallback playlistCallback) {
        Call<List<Playlist>> call = mService.getUserPlaylistsById(login, "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                int code = response.code();
                if (response.isSuccessful()) {
                    playlistCallback.onPlaylistsByUser((ArrayList<Playlist>) response.body());
                } else {
                    Log.d(TAG, "Error Not Successful: " + code);
                    playlistCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d(TAG, "Error Failure: " + t.getStackTrace());
                playlistCallback.onFailure(new Throwable("ERROR " + t.getStackTrace()));
            }
        });
    }

    /**********************
    * Get the playlist the current user follows
     **********************/
    public synchronized void getFollowingPlaylists (final PlaylistCallback playlistCallback) {
        Call<List<Playlist>> call = mService.getFollowingPlaylists("Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                int code = response.code();
                if (response.isSuccessful()) {
                    ArrayList<Playlist> playlists = (ArrayList<Playlist>) response.body();
                    playlistCallback.onFollowingList(playlists);

                } else {
                    Log.d(TAG, "Error Not Successful: " + code);
                    playlistCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d(TAG, "Error Failure: " + t.getStackTrace());
                playlistCallback.onFailure(new Throwable("ERROR " + t.getStackTrace()));
            }
        });
    }



}
