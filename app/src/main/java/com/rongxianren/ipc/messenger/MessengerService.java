package com.rongxianren.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.rongxianren.ipc.MyConstant;

public class MessengerService extends Service {


    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstant.MSG_FROM_CLIENT:
                    System.out.println("msg from Client: " + msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    private Handler mHander = new MessengerHandler();
    private final Messenger mMessenger = new Messenger(mHander);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
