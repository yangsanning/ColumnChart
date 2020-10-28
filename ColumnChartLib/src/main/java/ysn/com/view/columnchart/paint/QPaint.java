package ysn.com.view.columnchart.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @Author yangsanning
 * @ClassName QPaint
 * @Description 快捷画笔
 * @Date 2020/10/26
 */
public class QPaint {

    public Paint paint = new Paint();
    public Rect rect = new Rect();

    public QPaint() {
        // 设置抗锯齿
        setAntiAlias(true)
                // 设置画笔类型
                .setStyle(Paint.Style.FILL_AND_STROKE)
                // 设置字体居中
                .setTextAlign(Paint.Align.CENTER);
    }

    public QPaint setColor(int color) {
        paint.setColor(color);
        return this;
    }

    public QPaint setStyle(Paint.Style style) {
        paint.setStyle(style);
        return this;
    }

    public QPaint setTextSize(float textSize) {
        paint.setTextSize(textSize);
        return this;
    }

    public QPaint setAntiAlias(boolean aa) {
        paint.setAntiAlias(aa);
        return this;
    }

    public QPaint setTextAlign(Paint.Align align) {
        paint.setTextAlign(align);
        return this;
    }

    public QPaint setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
        return this;
    }

    public Rect getTextRect(String text) {
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    public QPaint drawText(Canvas canvas, String text, float x, float y) {
        canvas.drawText(text, x, y, paint);
        return this;
    }

    public QPaint drawText(Canvas canvas, int color, String text, float x, float y) {
        return setColor(color).drawText(canvas, text, x, y);
    }

    public QPaint drawText(Canvas canvas, int color, float textSize, String text, float x, float y) {
        return setTextSize(textSize).drawText(canvas, color, text, x, y);
    }

    /**
     * 绘制 y 轴居中文本
     *
     * @param y 文字中心 y 坐标
     */
    public QPaint drawTextCenterY(Canvas canvas, String text, float x, float y) {
        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
        return drawText(canvas, text, x, (y + baseLineY));
    }

    public QPaint drawLine(Canvas canvas, float startX, float startY, float stopX, float stopY) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        return this;
    }

    public QPaint drawLine(Canvas canvas, int color, float startX, float startY, float stopX, float stopY) {
        return setColor(color).drawLine(canvas, startX, startY, stopX, stopY);
    }
}
