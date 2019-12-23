package com.jiang.service.api.spring.service;

/**
 * Created by liyujiang on 2019/12/23.
 */
public interface IGarden {
    int getLength();


    int getWidth();

    /**
     * 地板材料
     * @return
     */
    String getFloorMaterial();
}
