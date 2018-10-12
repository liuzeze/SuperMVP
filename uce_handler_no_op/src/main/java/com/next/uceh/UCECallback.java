package com.next.uceh;


/**
 * <pre>
 *     author : NeXT
 *     time   : 2018/09/25
 *     desc   :
 * </pre>
 */
public interface UCECallback {

    void exceptionInfo( ExceptionInfoBean exceptionInfoBean);

    void throwable( Throwable throwable);

}
