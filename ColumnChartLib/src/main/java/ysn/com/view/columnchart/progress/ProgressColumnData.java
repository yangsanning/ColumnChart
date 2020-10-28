package ysn.com.view.columnchart.progress;

/**
 * @Author yangsanning
 * @ClassName ProgressColumnData
 * @Description 一句话概括作用
 * @Date 2020/10/27
 */
public interface ProgressColumnData {

    float getMaxProgress();

    float getCurrentProgress();

    String getYCoordinate();
}
