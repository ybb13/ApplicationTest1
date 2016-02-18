package com.s10.ybb.com.applicationtest1.util;

/**
 * Created by Administrator on 2016/2/7.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
