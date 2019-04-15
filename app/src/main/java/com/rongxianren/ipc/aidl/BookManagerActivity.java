package com.rongxianren.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rongxianren.ipc.R;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {


    private IBookManager mIBookManagerBinder;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("---BookManagerActivity---onServiceConnected = " + name);
            mIBookManagerBinder = IBookManager.Stub.asInterface(service);
            try {
                mIBookManagerBinder.registerListener(mIOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIBookManagerBinder = null;
            System.out.println("---BookManagerActivity---onServiceDisconnected = " + name);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
        if (mIBookManagerBinder != null && mIBookManagerBinder.asBinder().isBinderAlive()) {
        }
    }

    public void getBookList(View view) {
        try {
            if (mIBookManagerBinder != null) {
                List<Book> books = mIBookManagerBinder.getBookList();
                System.out.println("--- getBookList--- = " + books);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBook(View view) {
        try {
            if (mIBookManagerBinder != null) {
                mIBookManagerBinder.addBook(new Book(1002, "java核心技术"));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    IOnNewBookArrivedListener mIOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            System.out.println(newBook.toString());
        }
    };
}
