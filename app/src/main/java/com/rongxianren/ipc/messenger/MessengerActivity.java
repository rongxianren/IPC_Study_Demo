package com.rongxianren.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rongxianren.ipc.MyConstant;
import com.rongxianren.ipc.R;

import java.nio.BufferUnderflowException;

public class MessengerActivity extends AppCompatActivity {

    private Messenger mMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);


        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMessenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };


        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    public void sendMessage(View view) {
        Message message = Message.obtain(null, MyConstant.MSG_FROM_CLIENT);
        Bundle bundle = new Bundle();
        bundle.putString("msg", "this msg from client");
        message.setData(bundle);
        try {
            if (null != mMessenger) {
                mMessenger.send(message);
            } else {
                System.out.println("service has not connect");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
