package com.rongxianren.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.ObjectStreamException;

public class Book implements Parcelable {
    private int mBookId;
    private String mBookName;

    public Book(int mBookId, String mBookName) {
        this.mBookId = mBookId;
        this.mBookName = mBookName;
    }


    protected Book(Parcel in) {
        mBookId = in.readInt();
        mBookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mBookId);
        dest.writeString(mBookName);
    }

    private Object readResolve() throws ObjectStreamException {
        return null;
    }
}
