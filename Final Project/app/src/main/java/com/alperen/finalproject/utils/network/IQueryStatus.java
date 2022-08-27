package com.alperen.finalproject.utils.network;

import java.util.List;

/**
 * Created by Alperen on 27.08.2022.
 */
public interface IQueryStatus {
    void processing();
    <T> void success(List<T> list);
    void fail(String title, String msg);
}
