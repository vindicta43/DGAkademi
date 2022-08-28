package com.alperen.finalproject.utils.network;

import java.util.List;

/**
 * Created by Alperen on 28.08.2022.
 */
public interface IAuthStatus {
    void processing();
    void success(String operation, String msg);
    void fail(String title, String msg);
}
