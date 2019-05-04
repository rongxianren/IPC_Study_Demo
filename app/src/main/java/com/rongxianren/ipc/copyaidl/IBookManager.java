package com.rongxianren.ipc.copyaidl;

import android.os.IInterface;

import com.rongxianren.ipc.aidl.Book;

import java.util.List;

public interface IBookManager extends IInterface {

    List<Book> getBookList() throws android.os.RemoteException;

    void addBook(Book book) throws android.os.RemoteException;

    void registerListener(IOnNewBookArrivedListener listener) throws android.os.RemoteException;

    void unRegisterListener(IOnNewBookArrivedListener listener) throws android.os.RemoteException;

}
