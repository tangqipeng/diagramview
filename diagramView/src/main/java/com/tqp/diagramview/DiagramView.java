package com.tqp.diagramview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.tqp.diagramview.exception.DiagramException;

/**
 * @author tangqipeng
 * @date 2020/11/6 2:05 PM
 * @email tangqipeng@aograph.com
 */
public class DiagramView extends View {
    private final static String TAG = DiagramView.class.getName();

    private final float mXAxisSurplus;//x轴最后留有的剩余长度
    private final float mYAxisSurplus;//y轴最后留有的剩余长度
    private final float mPaddingTop;//距上
    private final float mPaddingBottom;//距下
    private final float mPaddingLeft;//距左
    private final float mPaddingRight;//距右
    private final boolean mHasArrows;//是否显示箭头
    private final int mYAxisTextSize;//y轴左侧的文字大小
    private final int mYAxisTextColor;//y轴左侧的文字颜色
    private final int mXAxisTextSize;//x轴左侧的文字大小
    private final int mXAxisTextColor;//x轴左侧的文字颜色
    private final int mYAxisVisible;//y轴是否显示
    private final int mYAxisCellLine;//y轴的个基准线是实线还是虚线

    private Paint mAxisPaint = null;
    private Paint mTextPaint = null;
    private Paint mItemPaint = null;
    private Adapter mAdapter = null;
    /**
     * x、y轴起点坐标的x
     */
    private float mAxis_x = 0f;
    /**
     * x、y轴起点坐标的y
     */
    private float mAxis_y = 0f;
    /**
     * x轴的终点x
     */
    private float mAxisX_end_X = 0f;
    /**
     * y轴的终点y
     */
    private float mAxisY_end_Y = 0f;

    private float offsetX = 0f;//x轴的偏移量

    private float mCellYSmallPix = 0f;//y值每单元值
    private float mCellYPix = 0f;//y轴每等份的长度
    private Paint mDottedLinePaint;//虚线paint

    private float mCellXPix = 0f;//x轴每等份的长度


    public DiagramView(Context context) {
        this(context, null);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DiagramView, defStyleAttr, 0);

        mXAxisSurplus = array.getFloat(R.styleable.DiagramView_xAxisSurplus, 0f);
        mYAxisSurplus = array.getFloat(R.styleable.DiagramView_yAxisSurplus, 0f);
        mPaddingTop = array.getDimension(R.styleable.DiagramView_paddingTop, 0f);
        mPaddingBottom = array.getDimension(R.styleable.DiagramView_paddingBottom, 0f);
        mPaddingLeft = array.getDimension(R.styleable.DiagramView_paddingLeft, 0f);
        mPaddingRight = array.getDimension(R.styleable.DiagramView_paddingRight, 0f);
        mHasArrows = array.getBoolean(R.styleable.DiagramView_hasArrows, true);
        mYAxisTextSize = array.getInteger(R.styleable.DiagramView_yAxisTextSize, 15);
        mXAxisTextSize = array.getInteger(R.styleable.DiagramView_xAxisTextSize, 15);
        mYAxisTextColor = array.getColor(R.styleable.DiagramView_yAxisTextColor, getResources().getColor(R.color.star_dust));
        mXAxisTextColor = array.getColor(R.styleable.DiagramView_xAxisTextColor, getResources().getColor(R.color.star_dust));
        mYAxisVisible = array.getInt(R.styleable.DiagramView_yAxisVisible, 0);
        mYAxisCellLine = array.getInt(R.styleable.DiagramView_yAxisCellLine, 0);
        array.recycle();
        init();
    }

    private void init() {
        mAxisPaint = new Paint();
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setStrokeWidth(4);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(4);

        mItemPaint = new Paint();
        mItemPaint.setAntiAlias(true);
        mItemPaint.setStrokeWidth(4);

        // 虚线paint
        mDottedLinePaint = new Paint();
        mDottedLinePaint.setColor(getResources().getColor(R.color.mystic));
        mDottedLinePaint.setStyle(Paint.Style.STROKE);
        mDottedLinePaint.setStrokeWidth(2);
        PathEffect effects = new DashPathEffect(new float[]{10, 5}, 1);//意思是所画虚线规则是先画10个长度的实线，留下5个长度的空白
        mDottedLinePaint.setPathEffect(effects);

    }

    public void setAdapter(Adapter adapter) {
        if (adapter == null)
            return;
        this.mAdapter = adapter;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int diagramWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int diagramHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "diagramWidth is " + diagramWidth);
        Log.i(TAG, "diagramHeight is " + diagramHeight);

        mTextPaint.setTextSize(mYAxisTextSize);
        @SuppressLint("DrawAllocation") Rect bounds = new Rect();
        mTextPaint.getTextBounds(mAdapter.getYAxleBaseCellText(mAdapter.getItemCount() - 1), 0, mAdapter.getYAxleBaseCellText(mAdapter.getItemCount() - 1).length(), bounds);
        int width = bounds.width();

        mAxis_x = mPaddingLeft + width + 10;

        mTextPaint.setTextSize(mXAxisTextSize);
        mAxis_y = diagramHeight - mPaddingBottom - mTextPaint.getTextSize() - 10;

        mAxisX_end_X = diagramWidth - 5 - mPaddingRight;
        mAxisY_end_Y = 5 + mPaddingTop;

        float axisWidth = mAxisX_end_X - mAxis_x;
        float axisHeight = mAxis_y - mAxisY_end_Y;

        mCellXPix = (axisWidth - mXAxisSurplus - mAdapter.getItemWidth()) / mAdapter.getItemCount();
        Log.i(TAG, "mCellXPix is " + mCellXPix);
        if (mHasArrows && mYAxisSurplus == 0) {
            mCellYPix = (axisHeight - mYAxisSurplus - 20) / mAdapter.getYAxleBaseCellNum();
        } else {
            mCellYPix = (axisHeight - mYAxisSurplus) / mAdapter.getYAxleBaseCellNum();
        }
        mCellYSmallPix = mCellYPix / mAdapter.getYAxleBaseCellSegmentationNum();

        offsetX = mAdapter.getItemWidth() / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            drawAxis(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawAxis(Canvas canvas) throws Exception {
        if (mAdapter == null)
            return;
        mAxisPaint.setColor(getResources().getColor(mAdapter.getXAxleColor()));
        canvas.drawLine(mAxis_x, mAxis_y, mAxisX_end_X, mAxis_y, mAxisPaint);//绘制x轴
        if (mHasArrows) {//绘制箭头
            canvas.drawLine(mAxisX_end_X, mAxis_y, mAxisX_end_X - 12, mAxis_y - 10, mAxisPaint);
            canvas.drawLine(mAxisX_end_X, mAxis_y, mAxisX_end_X - 12, mAxis_y + 10, mAxisPaint);
        }
        if (mYAxisVisible == VISIBLE){
            mAxisPaint.setColor(getResources().getColor(mAdapter.getYAxleColor()));
            canvas.drawLine(mAxis_x, mAxis_y, mAxis_x, mAxisY_end_Y, mAxisPaint);//绘制y轴
            if (mHasArrows) {//绘制箭头
                canvas.drawLine(mAxis_x, mAxisY_end_Y, mAxis_x - 10, mAxisY_end_Y + 12, mAxisPaint);
                canvas.drawLine(mAxis_x, mAxisY_end_Y, mAxis_x + 10, mAxisY_end_Y + 12, mAxisPaint);
            }
        }
        mTextPaint.setColor(mYAxisTextColor);
        mTextPaint.setTextSize(mXAxisTextSize);
        canvas.drawText("0", mPaddingLeft, mAxis_y + mTextPaint.getTextSize(), mTextPaint);

        drawYCell(canvas);
        if (mAdapter.getItemCount() > 0) {
            drawXCellAndItem(canvas, mAdapter);
        }
    }

    private void drawYCell(Canvas canvas) {
        mTextPaint.setTextSize(mYAxisTextSize);
        for (int i = 0; i < mAdapter.getYAxleBaseCellNum(); i++) {
            for (int j = 0; j < mAdapter.getYAxleBaseCellSegmentationNum(); j++) {
                canvas.drawLine(mAxis_x, mAxis_y - (mCellYPix * i) - (mCellYSmallPix * (j + 1)), mAxis_x + 10, mAxis_y - (mCellYPix * i) - (mCellYSmallPix * (j + 1)), mAxisPaint);
            }
            if (mYAxisCellLine == 0) {
                Path mDottedPath = new Path();
                mDottedPath.moveTo(mAxis_x, mAxis_y - (mCellYPix * (i + 1)));
                mDottedPath.lineTo(mAxisX_end_X, mAxis_y - (mCellYPix * (i + 1)));
                canvas.drawPath(mDottedPath, mDottedLinePaint);
            } else {
                canvas.drawLine(mAxis_x, mAxis_y - (mCellYPix * (i + 1)), mAxisX_end_X, mAxis_y - (mCellYPix * (i + 1)), mAxisPaint);
            }
            canvas.drawText(mAdapter.getYAxleBaseCellText(i), mPaddingLeft, mAxis_y - (mCellYPix * (i + 1)) + mTextPaint.getTextSize() / 2, mTextPaint);
        }
    }

    private void drawXCellAndItem(Canvas canvas, Adapter adapter) throws DiagramException {
        if (mAdapter.getItemColor() == 0) {
            throw new DiagramException("item的颜色值不能为0");
        }
        mItemPaint.setColor(getResources().getColor(mAdapter.getItemColor()));
        mAxisPaint.setColor(getResources().getColor(mAdapter.getXAxleColor()));
        Log.i(TAG, "adapter is " + adapter.getItemCount());

        mTextPaint.setColor(mXAxisTextColor);
        mTextPaint.setTextSize(mXAxisTextSize);
        for (int i = 0; i < adapter.getItemCount(); i++) {
            canvas.drawLine(mAxis_x + ((i + 1) * mCellXPix) - offsetX, mAxis_y, mAxis_x + ((i + 1) * mCellXPix) - offsetX, mAxis_y - 10, mAxisPaint);
            canvas.drawText(adapter.getXAxisText(i), mAxis_x + ((i + 1) * mCellXPix) - mTextPaint.getTextSize() - offsetX, mAxis_y + mTextPaint.getTextSize() + 5, mTextPaint);
            drawItem(canvas, mAxis_x + ((i + 1) * mCellXPix) - mAdapter.getItemWidth() / 2, mAxis_y - adapter.getItemHigh(i) * mCellYSmallPix / 10, mAxis_x + ((i + 1) * mCellXPix) + mAdapter.getItemWidth() / 2, mAxis_y);
        }
        Log.i(TAG, "drawXCell");
    }

    private void drawItem(Canvas canvas, float left, float top, float right, float bottom) {
        RectF rect = new RectF(left - offsetX, top, right - offsetX, bottom);
        canvas.drawRect(rect, mItemPaint);
    }

    public abstract static class Adapter {

        @ColorRes
        public abstract int getAxleXColor();

        @ColorRes
        public abstract int getAxleYColor();

        public int getXAxleColor() {
            if (getAxleXColor() == 0) {
                return R.color.mystic;
            } else {
                return getAxleXColor();
            }
        }

        public int getYAxleColor() {
            if (getAxleYColor() == 0) {
                return R.color.mystic;
            } else {
                return getAxleYColor();
            }
        }

        //下面才是必要，上面的转换为在xml中设置

        public abstract int getItemCount();

        public abstract int getYAxleBaseCellNum();

        /**
         * y轴的基本单元值
         *
         * @return
         */
        public abstract int getYAxleBaseCell();

        /**
         * y轴每个基本单元对应的数字
         *
         * @return
         */
        public abstract String getYAxleBaseCellText(int position);

        /**
         * y轴的最小单元值
         *
         * @return
         */
        public int getYAxleSmallestCell() throws DiagramException {
            if (getYAxleBaseCellSegmentationNum() < 0)
                throw new DiagramException("基本单元分段数不能小于0");
            if (getYAxleBaseCellSegmentationNum() == 0) {
                return getYAxleBaseCell();
            } else {
                return getYAxleBaseCell() / getYAxleBaseCellSegmentationNum();
            }
        }

        /**
         * y轴的基本单元分成几段
         *
         * @return
         */
        public abstract int getYAxleBaseCellSegmentationNum();

        /**
         * todo 待实现，这里是实现多个竖状图并列的方法
         *
         * @param position
         * @return
         */
        public int getItemViewType(int position) {
            return 0;
        }

        /**
         * 每个竖状图的颜色
         *
         * @return
         */
        @ColorRes
        public abstract int getItemColor();

        /**
         * 竖状图的id
         *
         * @param position
         * @return
         */
        public long getItemId(int position) {
            return NO_ID;
        }

        /**
         * x轴每个单元对应的文字
         *
         * @param position
         * @return
         */
        public abstract String getXAxisText(int position);

        /**
         * 获取
         *
         * @return
         */
        public abstract float getItemWidth();

        /**
         * 每个竖状图的高度
         *
         * @param position
         * @return
         */
        public abstract float getItemHigh(int position);

    }
}
