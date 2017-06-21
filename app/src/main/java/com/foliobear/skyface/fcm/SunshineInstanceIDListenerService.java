package com.foliobear.skyface.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Joseph Costlow on 10-Apr-17.
 */

public class SunshineInstanceIDListenerService extends FirebaseInstanceIdService {

    private final String TAG = SunshineInstanceIDListenerService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        Log.i(TAG, "sendRegistrationToServer: Token : " + token);
    }
}
