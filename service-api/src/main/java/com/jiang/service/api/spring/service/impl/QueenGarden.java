package com.jiang.service.api.spring.service.impl;

import com.jiang.service.api.spring.service.IGarden;

/**
 * Created by liyujiang on 2019/12/23.
 */
public class QueenGarden implements IGarden {

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public String getFloorMaterial() {
        return "Queen";
    }

    @Override
    public String toString() {
        return "floor:" + getFloorMaterial() + ", length:" + getLength() + ", width:" + getWidth();
    }
}
