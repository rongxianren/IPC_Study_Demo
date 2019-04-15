// IOnNewBookArrivedListener.aidl
package com.rongxianren.ipc.aidl;

// Declare any non-default types here with import statements
import com.rongxianren.ipc.aidl.Book;
interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
