// IBookManager.aidl
package com.rongxianren.ipc.aidl;

// Declare any non-default types here with import statements

import com.rongxianren.ipc.aidl.Book;

interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);
}
