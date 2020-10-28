package ysn.com.demo.columnchart.bean;

import ysn.com.view.columnchart.progress.ProgressColumnData;

/**
 * @Author yangsanning
 * @ClassName ProgressColumn
 * @Description 一句话概括作用
 * @Date 2020/10/27
 */
public class ProgressColumn implements ProgressColumnData {

    public float maxProgress;
    public float currentProgress;
    public String yCoordinate;

    public ProgressColumn(float maxProgress,float currentProgress,String yCoordinate) {
        this.maxProgress = maxProgress;
        this.currentProgress = currentProgress;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public float getMaxProgress() {
        return maxProgress;
    }

    @Override
    public float getCurrentProgress() {
        return currentProgress;
    }

    @Override
    public String getYCoordinate() {
        return yCoordinate;
    }
}
