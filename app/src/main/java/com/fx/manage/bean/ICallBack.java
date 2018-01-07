package com.fx.manage.bean;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/10.
 */
public interface ICallBack<T> {
    void resultForObject(T object);
    void resultForList(List<T> list);
}
