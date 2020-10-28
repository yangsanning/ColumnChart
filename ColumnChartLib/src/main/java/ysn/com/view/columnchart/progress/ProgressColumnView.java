package ysn.com.view.columnchart.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ysn.com.view.columnchart.R;
import ysn.com.view.columnchart.paint.QPaint;
import ysn.com.view.columnchart.utils.Utils;

/**
 * @Author yangsanning
 * @ClassName ProgressColumnView
 * @Description 一句话概括作用
 * @Date 2020/10/26
 */
public class ProgressColumnView extends View {

    /**
     * 左右留白
     */
    private static final int SPACE = 15;

    private List<ProgressColumnData> datas = new ArrayList<>();
    private List<String> xCoordinateList = new ArrayList<>();
    private float maxXCoordinate;

    private QPaint qPaint;

    private int lineColor, textColor, maxProgressColor, currentProgressColor;
    private int textSize, leftTableWidth, bottomBottomHeight;

    private int viewWidth, viewHeight;
    private float[] circle = new float[2];
    /**
     * 表格可用区间(减去padding的)
     * minX = tableFrame[0], maxX = tableFrame[1], minY = tableFrame[2], maxY = tableFrame[3]
     */
    private float[] tableUsableFrame = new float[4];

    public ProgressColumnView(Context context) {
        this(context, null);
    }

    public ProgressColumnView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressColumnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initPaint();
    }

    private void initPaint() {
        qPaint = new QPaint()
                .setTextSize(textSize);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressColumnView);

        lineColor = typedArray.getColor(R.styleable.ProgressColumnView_lineColor, Utils.getColor(context, R.color.progress_column_view_line));
        textColor = typedArray.getColor(R.styleable.ProgressColumnView_lineColor, Utils.getColor(context, R.color.progress_column_view_text));
        textSize = typedArray.getDimensionPixelSize(R.styleable.ProgressColumnView_textSize, 35);
        leftTableWidth = typedArray.getDimensionPixelSize(R.styleable.ProgressColumnView_leftTableWidth, 60);
        bottomBottomHeight = typedArray.getDimensionPixelSize(R.styleable.ProgressColumnView_bottomBottomHeight, 60);

        maxProgressColor = typedArray.getColor(R.styleable.ProgressColumnView_maxProgressColor,
                Utils.getColor(context, R.color.progress_column_view_max_progress));
        currentProgressColor = typedArray.getColor(R.styleable.ProgressColumnView_currentProgressColor,
                Utils.getColor(context, R.color.progress_column_view_current_progress));

        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;

        tableUsableFrame[0] = getPaddingLeft();
        tableUsableFrame[1] = viewWidth - getPaddingRight();
        tableUsableFrame[2] = getPaddingTop();
        tableUsableFrame[3] = viewHeight - getPaddingBottom();

        circle[0] = tableUsableFrame[0] + leftTableWidth;
        circle[1] = tableUsableFrame[3] - bottomBottomHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (xCoordinateList.isEmpty()) {
            return;
        }

        canvas.save();
        canvas.translate(getCircleX(), getCircleY());

        qPaint.setColor(textColor).setStrokeWidth(1.0f);
        float lineSpace = getUsableHeight() / xCoordinateList.size();
        for (int i = 0; i < xCoordinateList.size(); i++) {
            float stopY = -lineSpace * i;
            qPaint.drawTextCenterY(canvas, xCoordinateList.get(i), (-leftTableWidth / 2f), stopY)
                    .drawLine(canvas, lineColor, 0, stopY, getUsableWidth(), stopY);
        }

        if (!datas.isEmpty()) {
            float columnWidth = (getUsableWidth() - SPACE * 2) / (datas.size() * 2 - 1);
            for (int i = 0; i < datas.size(); i++) {
                ProgressColumnData data = datas.get(i);
                float x = columnWidth * ((i - 1) * 2 + 2) + SPACE + columnWidth / 2;
                qPaint.setStrokeWidth(1.0f)
                        .drawLine(canvas, lineColor, x, (0), x, SPACE)
                        .drawTextCenterY(canvas, datas.get(i).getYCoordinate(), x, ((bottomBottomHeight - SPACE) / 2f + SPACE))
                        .setStrokeWidth(columnWidth)
                        .drawLine(canvas, maxProgressColor, x, (0), x,
                                -((data.getMaxProgress() / maxXCoordinate) * (getUsableHeight() - lineSpace)))
                        .drawLine(canvas, currentProgressColor, x, (0), x,
                                -((data.getCurrentProgress() / maxXCoordinate) * (getUsableHeight() - lineSpace)));
            }
        }

        canvas.restore();
    }

    protected float getCircleX() {
        return circle[0];
    }

    protected float getCircleY() {
        return circle[1];
    }

    /**
     * 获取表格可用的高度(原点y 到 paddingTop的距离)
     */
    protected float getUsableHeight() {
        return getCircleY() - tableUsableFrame[2];
    }

    /**
     * 获取表格可用的高度(原点x 到 viewWidth - paddingEnd的距离)
     */
    protected float getUsableWidth() {
        return tableUsableFrame[1] - getCircleX();
    }

    public void setXCoordinateList(List<String> xCoordinateList, float maxXCoordinate) {
        this.xCoordinateList = xCoordinateList;
        this.maxXCoordinate = maxXCoordinate;
        invalidate();
    }

    public void setDatas(List<ProgressColumnData> datas) {
        if (datas == null) {
            return;
        }
        this.datas = datas;

        invalidate();
    }
}
