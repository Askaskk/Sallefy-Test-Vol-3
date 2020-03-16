package com.salle.android.sallefy_test_vol_3.controller.restapi.callback;

import com.salle.android.sallefy_test_vol_3.model.User;
import com.salle.android.sallefy_test_vol_3.model.UserToken;

import java.util.List;

public interface UserCallback extends FailureCallback {
    void onLoginSuccess(UserToken userToken);
    void onLoginFailure(Throwable throwable);
    void onRegisterSuccess();
    void onRegisterFailure(Throwable throwable);
    void onUserInfoReceived(User userData);
    void onUsersReceived(List<User> users);
    void onUsersFailure(Throwable throwable);
}
