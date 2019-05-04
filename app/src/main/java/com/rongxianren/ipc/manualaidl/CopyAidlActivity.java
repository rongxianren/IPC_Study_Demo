package com.rongxianren.ipc.manualaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rongxianren.ipc.R;
import com.rongxianren.ipc.aidl.Book;

import java.util.List;

public class CopyAidlActivity extends AppCompatActivity {


    IBookManager bookManager;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookManagerImpl.asInterface(service);

            try {
                bookManager.registerListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private IOnNewBookArrivedListenerImpl onNewBookArrivedListener = new IOnNewBookArrivedListenerImpl() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            System.out.println(newBook.toString());
            System.out.println(" ---onNewBookArrived--- currentThread = " + Thread.currentThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_aidl);
        Intent intent = new Intent(this, CopyAidlService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        if (bookManager != null && bookManager.asBinder().isBinderAlive()) {
            try {
                bookManager.unRegisterListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBook(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("---CopyAidlActivity  addBook --- " + Thread.currentThread().getName());
                    bookManager.addBook(new Book(1002, "java核心技术"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void getBook(View view) {
        try {
            List<Book> books = bookManager.getBookList();
            System.out.println("--- getBookList--- = " + books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
