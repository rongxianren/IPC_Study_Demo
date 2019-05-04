package com.rongxianren.ipc.manualaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.rongxianren.ipc.aidl.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyAidlService extends Service {

    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> listenerList = new RemoteCallbackList<>();

    IBookManagerImpl bookManager = new IBookManagerImpl() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
//            bookList.add(book);
            System.out.println("---CopyAidlService addBook --- CurrentThread " + Thread.currentThread().getName());
            onNewBookArrived(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            listenerList.register(listener);

        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            listenerList.unregister(listener);
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        bookList.add(new Book(1001, "乡土中国"));
        bookList.add(new Book(1002, "怪诞行为学"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }


    private void onNewBookArrived(Book book) throws RemoteException {
        bookList.add(book);
        int count = listenerList.beginBroadcast();
        for (int i = 0; i < count; i++) {
            IOnNewBookArrivedListener listener = listenerList.getBroadcastItem(i);
            listener.onNewBookArrived(book);
        }
        listenerList.finishBroadcast();
    }

}
