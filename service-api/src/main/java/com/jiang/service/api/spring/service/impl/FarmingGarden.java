package com.jiang.service.api.spring.service.impl;

import com.jiang.service.api.spring.service.IGarden;

/**
 * Created by liyujiang on 2019/12/23.
 */
public class FarmingGarden implements IGarden {

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public String getFloorMaterial() {
        return "Farming";
    }

    @Override
    public String toString() {
        return "floor:" + getFloorMaterial() + ", length:" + getLength() + ", width:" + getWidth();
    }
}
