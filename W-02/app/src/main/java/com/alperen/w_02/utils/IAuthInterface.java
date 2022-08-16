package com.alperen.w_02.utils;

/**
 * Created by Alperen on 16.08.2022.
 */
public interface IAuthInterface {
    void processing();
    void success(String action);
    void fail(String title, String msg);
}
