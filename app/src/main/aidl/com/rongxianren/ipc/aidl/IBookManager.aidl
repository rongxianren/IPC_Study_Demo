// IBookManager.aidl
package com.rongxianren.ipc.aidl;

// Declare any non-default types here with import statements

import com.rongxianren.ipc.aidl.Book;
import com.rongxianren.ipc.aidl.IOnNewBookArrivedListener;
interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(IOnNewBookArrivedListener listener);

    void unRegisterListener(IOnNewBookArrivedListener listener);

}
