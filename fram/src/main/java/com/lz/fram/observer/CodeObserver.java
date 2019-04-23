package com.lz.fram.observer;

import com.lz.fram.base.BaseEntity;


public abstract class CodeObserver<T> extends CommonObserver<BaseEntity<T>> {

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        onSuccess(tBaseEntity.getData());
        //自己定义code处理
        switch (tBaseEntity.getCode()) {
            case 200:
                onSuccess(tBaseEntity.getData());
                break;
            case 300:
            case 500:
                onCodeError(tBaseEntity.getCode(), tBaseEntity.getMsg());
                break;
            default:
        }
    }


    protected abstract void onSuccess(T data);

    protected abstract void onCodeError(int code, String mes);
}
