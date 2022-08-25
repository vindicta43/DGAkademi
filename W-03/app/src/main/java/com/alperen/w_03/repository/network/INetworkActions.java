package com.alperen.w_03.repository.network;

/**
 * Created by Alperen on 24.08.2022.
 */
public interface INetworkActions {
    void processing();
    void success(String msgTitle, String msgBody);
    void fail(String msgTitle, String msgBody);
}
