package com.rongxianren.ipc.copyaidl;

import android.os.Binder;
import android.os.IBinder;

public abstract class IOnNewBookArrivedListenerImpl extends Binder implements IOnNewBookArrivedListener {


    private static final java.lang.String DESCRIPTOR = "com.rongxianren.ipc.aidl.IOnNewBookArrivedListener";

    /**
     * Construct the stub at attach it to the interface.
     */
    public IOnNewBookArrivedListenerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an com.rongxianren.ipc.aidl.IOnNewBookArrivedListener interface,
     * generating a proxy if needed.
     */
    public static IOnNewBookArrivedListener asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IOnNewBookArrivedListener))) {
            return ((IOnNewBookArrivedListener) iin);
        }
        return new IOnNewBookArrivedListenerImpl.Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        java.lang.String descriptor = DESCRIPTOR;
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }
            case TRANSACTION_onNewBookArrived: {
                data.enforceInterface(descriptor);
                com.rongxianren.ipc.aidl.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = com.rongxianren.ipc.aidl.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.onNewBookArrived(_arg0);
                reply.writeNoException();
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }

    private static class Proxy implements IOnNewBookArrivedListener {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public void onNewBookArrived(com.rongxianren.ipc.aidl.Book newBook) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((newBook != null)) {
                    _data.writeInt(1);
                    newBook.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                ///flags 默认为0 同步实现   也可指定为 FLAG_ONEWAY 异步实现client端
                // 无需等待server端返回执行结果 而是立即继续往下执行
                mRemote.transact(IOnNewBookArrivedListenerImpl.TRANSACTION_onNewBookArrived, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

    static final int TRANSACTION_onNewBookArrived = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

}
