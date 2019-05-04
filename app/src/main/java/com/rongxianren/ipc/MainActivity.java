package com.rongxianren.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rongxianren.ipc.aidl.BookManagerActivity;
import com.rongxianren.ipc.manualaidl.CopyAidlActivity;
import com.rongxianren.ipc.messenger.MessengerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goBookManagerActivity(View view) {
        Intent intent = new Intent(this, BookManagerActivity.class);
        startActivity(intent);
    }

    public void goMessengerActivity(View view) {
        Intent intent = new Intent(this, MessengerActivity.class);
        startActivity(intent);
    }

    public void goCopyAidlActivity(View view) {
        Intent intent = new Intent(this, CopyAidlActivity.class);
        startActivity(intent);
    }
}
