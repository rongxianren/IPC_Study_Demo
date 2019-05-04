package com.rongxianren.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList();

    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList();
    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("---getBookList--- currentThread = " + Thread.currentThread().getName());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            mBookList.add(book);
            onNewBookArrived(book);
            System.out.println("---addBook--- currentThread = " + Thread.currentThread().getName());
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener);
//            } else {
//                System.out.println("---already exists ");
//            }
//
//            System.out.println(" mListenerList size = " + mListenerList.size());

        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.unregister(listener);

//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener);
//                System.out.println("---unRegisterListener  success---  ");
//            } else {
//                System.out.println("---has not find listener---  ");
//            }

            System.out.println(" mListenerList size = " + mListenerList.getRegisteredCallbackCount());
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1001, "乡土中国"));
        mBookList.add(new Book(1002, "怪诞行为学"));
        Thread thread = new Thread(new ServiceWorker());
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestoryed.set(true);
        System.out.println(" ---BookManagerService--- onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.rongxianren.ipc.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            System.out.println("onBind fail permission deny");
            return null;
        }
        return mBinder;
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        int count = mListenerList.beginBroadcast();
        for (int i = 0; i < count; i++) {
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            listener.onNewBookArrived(book);
        }
        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size();
                Book book = new Book(bookId, "new book # " + bookId);
                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
