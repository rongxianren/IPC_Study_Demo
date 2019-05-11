package com.rongxianren.ipc.manualaidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.rongxianren.ipc.aidl.Book;

import java.util.List;

public abstract class IBookManagerImpl extends Binder implements IBookManager {

    private static final java.lang.String DESCRIPTOR = "com.rongxianren.ipc.aidl.IBookManager";

    /**
     * Construct the stub at attach it to the interface.
     */
    public IBookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an com.rongxianren.ipc.aidl.IBookManager interface,
     * generating a proxy if needed.
     */
    public static IBookManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IBookManager))) {
            return ((IBookManager) iin);
        }
        return new IBookManagerImpl.Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        java.lang.String descriptor = DESCRIPTOR;
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(descriptor);
                java.util.List<Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(descriptor);
                Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_registerListener: {
                data.enforceInterface(descriptor);
                IOnNewBookArrivedListener _arg0;
                _arg0 = IOnNewBookArrivedListenerImpl.asInterface(data.readStrongBinder());
                this.registerListener(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_unRegisterListener: {
                data.enforceInterface(descriptor);
                IOnNewBookArrivedListener _arg0;
                _arg0 = IOnNewBookArrivedListenerImpl.asInterface(data.readStrongBinder());
                this.unRegisterListener(_arg0);
                reply.writeNoException();
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }

    private static class Proxy implements IBookManager {
        private IBinder mRemote;

        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            android.os.Parcel _data = Parcel.obtain();
            android.os.Parcel _reply = Parcel.obtain();
            java.util.List<com.rongxianren.ipc.aidl.Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(IBookManagerImpl.TRANSACTION_getBookList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(Book.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(IBookManagerImpl.TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws android.os.RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                mRemote.transact(IBookManagerImpl.TRANSACTION_registerListener, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                mRemote.transact(IBookManagerImpl.TRANSACTION_unRegisterListener, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

    static final int TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_registerListener = (IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_unRegisterListener = (IBinder.FIRST_CALL_TRANSACTION + 3);

}
