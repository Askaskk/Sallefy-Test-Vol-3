package com.salle.android.sallefy_test_vol_3.controller.restapi.callback;

import com.salle.android.sallefy_test_vol_3.model.User;
import com.salle.android.sallefy_test_vol_3.model.UserToken;

public interface UserCallback extends FailureCallback {
    void onLoginSuccess(UserToken userToken);
    void onLoginFailure(Throwable throwable);
    void onRegisterSuccess();
    void onRegisterFailure(Throwable throwable);
    void onUserInfoReceived(User userData);
}
