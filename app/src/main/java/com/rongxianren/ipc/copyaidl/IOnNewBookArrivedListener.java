package com.rongxianren.ipc.copyaidl;

import android.os.IBinder;
import android.os.IInterface;

import com.rongxianren.ipc.aidl.Book;

public interface IOnNewBookArrivedListener extends IInterface {

    void onNewBookArrived(Book newBook) throws android.os.RemoteException;
}
