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

import java.util.ArrayList;
import java.util.List;

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
    private final int mYAxisColor;//y轴的颜色
    private final int mRightYAxisColor;//右侧y轴的颜色
    private final int mXAxisColor;//x轴的颜色
    private final int mYAxisTextSize;//y轴左侧的文字大小
    private final int mRightYAxisTextSize;//右侧y轴左侧的文字大小
    private final int mYAxisTextColor;//y轴左侧的文字颜色
    private final int mRightYAxisTextColor;//右侧y轴左侧的文字颜色
    private final int mXAxisTextSize;//x轴左侧的文字大小
    private final int mXAxisTextColor;//x轴左侧的文字颜色
    private final int mYAxisVisible;//y轴是否显示
    private final int mYAxisCellLine;//y轴的个基准线是实线还是虚线

    private Paint mAxisPaint = null;
    private Paint mTextPaint = null;
    private Paint mItemPaint = null;
    private Paint mRightItemPaint = null;
    private Adapter mAdapter = null;

    private Path rightYPath = null;

    private float mItemsWidth = 0f;
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

    /**
     * 右侧纵轴的起点x
     */
    private float mRightAxisX = 0f;

    private float offsetX = 0f;//x轴的偏移量

    private float mCellYSmallPix = 0f;//y值每单元值
    private float mCellYPix = 0f;//y轴每等份的长度

    private Paint mDottedLinePaint;//虚线paint

    private float mRightCellYSmallPix = 0f;//右侧y值每单元值
    private float mRightCellYPix = 0f;//右侧y轴每等份的长度

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
        mXAxisSurplus = array.getDimension(R.styleable.DiagramView_xAxisSurplus, 0f);
        mYAxisSurplus = array.getDimension(R.styleable.DiagramView_yAxisSurplus, 0f);
        mPaddingTop = array.getDimension(R.styleable.DiagramView_paddingTop, 0f);
        mPaddingBottom = array.getDimension(R.styleable.DiagramView_paddingBottom, 0f);
        mPaddingLeft = array.getDimension(R.styleable.DiagramView_paddingLeft, 0f);
        mPaddingRight = array.getDimension(R.styleable.DiagramView_paddingRight, 0f);
        mHasArrows = array.getBoolean(R.styleable.DiagramView_hasArrows, true);
        mYAxisColor = array.getColor(R.styleable.DiagramView_yAxisColor, getResources().getColor(R.color.mystic));
        mRightYAxisColor = array.getColor(R.styleable.DiagramView_rightYAxisColor, getResources().getColor(R.color.mystic));
        mXAxisColor = array.getColor(R.styleable.DiagramView_xAxisColor, getResources().getColor(R.color.mystic));
        mYAxisTextSize = array.getInteger(R.styleable.DiagramView_yAxisTextSize, 15);
        mRightYAxisTextSize = array.getInteger(R.styleable.DiagramView_rightYAxisTextSize, 15);
        mXAxisTextSize = array.getInteger(R.styleable.DiagramView_xAxisTextSize, 15);
        mYAxisTextColor = array.getColor(R.styleable.DiagramView_yAxisTextColor, getResources().getColor(R.color.star_dust));
        mRightYAxisTextColor = array.getColor(R.styleable.DiagramView_rightYAxisTextColor, getResources().getColor(R.color.star_dust));
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

        mRightItemPaint = new Paint();
        mRightItemPaint.setAntiAlias(true);
        mRightItemPaint.setStrokeWidth(30f);
        mRightItemPaint.setStyle(Paint.Style.FILL);

        Log.i("PPPPPP", "init()");


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
        Log.i("PPPPPP", "setAdapter()");
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int diagramWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int diagramHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "diagramWidth is " + diagramWidth);
        Log.i(TAG, "diagramHeight is " + diagramHeight);

        Log.i("PPPPPP", "onMeasure()");

        mAxis_x = mPaddingLeft + 10;
        mAxis_y = diagramHeight - mPaddingBottom - 10;
        mAxisX_end_X = diagramWidth - 5 - mPaddingRight;
        mAxisY_end_Y = 5 + mPaddingTop;

        if (mAdapter == null)
            return;

        mTextPaint.setTextSize(mYAxisTextSize);
        @SuppressLint("DrawAllocation") Rect yRect = new Rect();
        String text = "";
        for (int i = 0; i < mAdapter.getYAxleBaseCellNum() - 1; i++) {
            if (mAdapter.getYAxleBaseCellText(i).length() < mAdapter.getYAxleBaseCellText(i + 1).length()) {
                text = mAdapter.getYAxleBaseCellText(i + 1);
            } else {
                text = mAdapter.getYAxleBaseCellText(i);
            }
        }
        Log.i(TAG, "text is " + text);
        mTextPaint.getTextBounds(text, 0, text.length(), yRect);
        int width = yRect.width();
        Log.i(TAG, "width is " + width);
        mAxis_x += width;
        Log.i(TAG, "mAxis_x is " + mAxis_x);

        mTextPaint.setTextSize(mXAxisTextSize);
        mAxis_y -= mTextPaint.getTextSize();

        if (mAdapter.openRightYAxle()) {
            mTextPaint.setTextSize(mRightYAxisTextSize);
            @SuppressLint("DrawAllocation") Rect rightYRect = new Rect();
            String rightText = "";
            for (int i = 0; i < mAdapter.getRightYAxleBaseCellNum() - 1; i++) {
                if (mAdapter.getRightYAxleBaseCellText(i).length() < mAdapter.getRightYAxleBaseCellText(i + 1).length()) {
                    rightText = mAdapter.getRightYAxleBaseCellText(i + 1);
                } else {
                    rightText = mAdapter.getRightYAxleBaseCellText(i);
                }
            }
            mTextPaint.getTextBounds(rightText, 0, rightText.length(), rightYRect);
            int rightWidth = rightYRect.width();
            mAxisX_end_X -= rightWidth;
        }

        float axisWidth = mAxisX_end_X - mAxis_x;
        float axisHeight = mAxis_y - mAxisY_end_Y;

        mItemsWidth = 0;
        for (int i = 0; i < mAdapter.getTypeCount(); i++) {
            mItemsWidth += mAdapter.getItemWidth(i);
        }
        Log.i(TAG, "mItemsWidth is " + mItemsWidth);

        if (mHasArrows && mXAxisSurplus == 0) {
            mCellXPix = (axisWidth - 20 - mItemsWidth / 2) / mAdapter.getItemCount();
        } else {
            mCellXPix = (axisWidth - mXAxisSurplus - mItemsWidth / 2) / mAdapter.getItemCount();
        }
        Log.i(TAG, "mCellXPix is " + mCellXPix);

        if (mItemsWidth / 2 > mCellXPix) {
            try {
                throw new DiagramException("item的宽度太大，超过了界限");
            } catch (DiagramException e) {
                e.printStackTrace();
            }
        }
        if (mHasArrows && mYAxisSurplus == 0) {
            mCellYPix = (axisHeight - 20) / mAdapter.getYAxleBaseCellNum();
        } else {
            mCellYPix = (axisHeight - mYAxisSurplus) / mAdapter.getYAxleBaseCellNum();
        }
        mCellYSmallPix = mCellYPix / mAdapter.getYAxleBaseCellSegmentationNum();

        if (mAdapter.openRightYAxle()) {
            mRightAxisX = mAxisX_end_X - mXAxisSurplus;
            if (mHasArrows && mYAxisSurplus == 0) {
                mRightCellYPix = (axisHeight - 20) / mAdapter.getRightYAxleBaseCellNum();
            } else {
                mRightCellYPix = (axisHeight - mYAxisSurplus) / mAdapter.getRightYAxleBaseCellNum();
            }
            mRightCellYSmallPix = mRightCellYPix / mAdapter.getRightYAxleBaseCellSegmentationNum();
        }

        offsetX = (mCellXPix - mItemsWidth / 2) / 2;
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
        mAxisPaint.setColor(mXAxisColor);
        canvas.drawLine(mAxis_x, mAxis_y, mAxisX_end_X, mAxis_y, mAxisPaint);//绘制x轴
        if (mHasArrows) {//绘制箭头
            canvas.drawLine(mAxisX_end_X, mAxis_y, mAxisX_end_X - 12, mAxis_y - 10, mAxisPaint);
            canvas.drawLine(mAxisX_end_X, mAxis_y, mAxisX_end_X - 12, mAxis_y + 10, mAxisPaint);
        }
        if (mYAxisVisible == VISIBLE) {
            mAxisPaint.setColor(mYAxisColor);
            canvas.drawLine(mAxis_x, mAxis_y, mAxis_x, mAxisY_end_Y, mAxisPaint);//绘制y轴
            if (mHasArrows) {//绘制箭头
                canvas.drawLine(mAxis_x, mAxisY_end_Y, mAxis_x - 10, mAxisY_end_Y + 12, mAxisPaint);
                canvas.drawLine(mAxis_x, mAxisY_end_Y, mAxis_x + 10, mAxisY_end_Y + 12, mAxisPaint);
            }
        }
        mTextPaint.setColor(mYAxisTextColor);
        mTextPaint.setTextSize(mXAxisTextSize);
        canvas.drawText("0", mPaddingLeft, mAxis_y + mTextPaint.getTextSize(), mTextPaint);

        Log.i("PPPPPP", "drawAxis");

        if (mAdapter == null)
            return;
        drawYCell(canvas);
        if (mAdapter.getItemCount() > 0) {
            drawXCellAndItem(canvas, mAdapter);
        }

        drawRightAsix(canvas);
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
        if (mAdapter.getItemColor(0) == 0) {
            throw new DiagramException("item的颜色值不能为0");
        }

        mAxisPaint.setColor(mXAxisColor);
        Log.i(TAG, "getItemCount is " + adapter.getItemCount());
        Log.i(TAG, "getTypeCount is " + adapter.getTypeCount());

        mTextPaint.setColor(mXAxisTextColor);
        mTextPaint.setTextSize(mXAxisTextSize);
        for (int i = 0; i < adapter.getItemCount(); i++) {
            canvas.drawLine(mAxis_x + ((i + 1) * mCellXPix) - offsetX, mAxis_y, mAxis_x + ((i + 1) * mCellXPix) - offsetX, mAxis_y - 10, mAxisPaint);
            canvas.drawText(adapter.getXAxisText(i), mAxis_x + ((i + 1) * mCellXPix) - mTextPaint.getTextSize() - offsetX, mAxis_y + mTextPaint.getTextSize() + 5, mTextPaint);
        }

        List<List<Float>> typeList = new ArrayList<>();
        List<Path> pathList = new ArrayList<>();
        float lastItemsWidth = 0f;
        for (int j = 0; j < adapter.getTypeCount(); j++) {
            if (mAdapter.getItemColor(j) == 0) {
                throw new DiagramException("item的颜色值不能为0");
            }
            List<Float> floats = new ArrayList<>();
            mItemPaint.setColor(getResources().getColor(mAdapter.getItemColor(j)));
            if (mAdapter.openRightYAxle()) {
                mRightItemPaint.setColor(getResources().getColor(mAdapter.getRightItemColor(j)));
                rightYPath = new Path();
                rightYPath.moveTo(mAxis_x + mCellXPix - offsetX, mAxis_y - adapter.getRightCellValue(j, 0) * mRightCellYSmallPix / mAdapter.getRightYAxleSmallestCell());
            }
            for (int i = 0; i < adapter.getItemCount(); i++) {
                drawItem(canvas, mAxis_x + ((i + 1) * mCellXPix) - mItemsWidth / 2 + lastItemsWidth, mAxis_y - adapter.getItemHigh(j, i) * mCellYSmallPix / mAdapter.getYAxleSmallestCell(), mAxis_x + ((i + 1) * mCellXPix) - mItemsWidth / 2 + lastItemsWidth + mAdapter.getItemWidth(j), mAxis_y);

                if (mAdapter.openRightYAxle()) {

                    floats.add(mAxis_x + ((i + 1) * mCellXPix) - offsetX);
                    floats.add(mAxis_y - adapter.getRightCellValue(j, i) * mRightCellYSmallPix / mAdapter.getRightYAxleSmallestCell());

                    if (i > 0) {
                        rightYPath.lineTo(mAxis_x + ((i + 1) * mCellXPix) - offsetX, mAxis_y - adapter.getRightCellValue(j, i) * mRightCellYSmallPix / mAdapter.getRightYAxleSmallestCell());
                    }
                }
            }
            lastItemsWidth += mAdapter.getItemWidth(j);
            if (mAdapter.openRightYAxle()) {
                typeList.add(floats);
                pathList.add(rightYPath);
            }
        }
        if (mAdapter.openRightYAxle()) {
            mRightItemPaint.setStrokeWidth(30f);
            mRightItemPaint.setStyle(Paint.Style.FILL);
            for (int j = 0; j < typeList.size(); j++) {
                mRightItemPaint.setColor(getResources().getColor(mAdapter.getRightItemColor(j)));
                float[] floats = new float[typeList.get(j).size()];
                for (int i = 0; i < typeList.get(j).size(); i++) {
                    floats[i] = typeList.get(j).get(i);
                }
                canvas.drawPoints(floats, mRightItemPaint);
            }

            mRightItemPaint.setStrokeWidth(4);
            mRightItemPaint.setStyle(Paint.Style.STROKE);
            for (int i = 0; i < pathList.size(); i++) {
                mRightItemPaint.setColor(getResources().getColor(mAdapter.getRightItemColor(i)));
                canvas.drawPath(pathList.get(i), mRightItemPaint);
            }
        }
        Log.i(TAG, "drawXCell");
    }

    private void drawItem(Canvas canvas, float left, float top, float right, float bottom) {
        RectF rect = new RectF(left - offsetX, top, right - offsetX, bottom);
        canvas.drawRect(rect, mItemPaint);
    }

    private void drawRightAsix(Canvas canvas) {
        if (mAdapter.openRightYAxle()) {
            mAxisPaint.setColor(mRightYAxisColor);
            if (mYAxisVisible == VISIBLE) {
                canvas.drawLine(mRightAxisX, mAxis_y, mRightAxisX, mAxisY_end_Y, mAxisPaint);
                if (mHasArrows) {//绘制箭头
                    canvas.drawLine(mRightAxisX, mAxisY_end_Y, mRightAxisX - 10, mAxisY_end_Y + 12, mAxisPaint);
                    canvas.drawLine(mRightAxisX, mAxisY_end_Y, mRightAxisX + 10, mAxisY_end_Y + 12, mAxisPaint);
                }
            }
            mTextPaint.setTextSize(mRightYAxisTextSize);
            mTextPaint.setColor(mRightYAxisTextColor);
            for (int i = 0; i < mAdapter.getRightYAxleBaseCellNum(); i++) {
                canvas.drawLine(mRightAxisX, mAxis_y - (mRightCellYPix * (i + 1)), mRightAxisX - 10, mAxis_y - (mRightCellYPix * (i + 1)), mAxisPaint);
                canvas.drawText(mAdapter.getRightYAxleBaseCellText(i), mRightAxisX + 10, mAxis_y - (mRightCellYPix * (i + 1)) + mTextPaint.getTextSize() / 2, mTextPaint);
            }
        }
    }

    public abstract static class Adapter implements RightYAxisListener {

        //下面才是必要，上面的转换为在xml中设置

        public abstract int getTypeCount();

        /**
         * 每组item的个数
         *
         * @return 每组item的个数
         */
        public abstract int getItemCount();

        /**
         * y轴的基本单元数，（这是指标有数值的单元）
         *
         * @return y轴的基本单元
         */
        public abstract int getYAxleBaseCellNum();

        /**
         * y轴的基本单元值
         *
         * @return y轴的基本单元值
         */
        public abstract int getYAxleBaseCell();

        /**
         * y轴每个基本单元对应的数字
         *
         * @return y轴每个基本单元对应的文字
         */
        public abstract String getYAxleBaseCellText(int position);

        /**
         * y轴的最小单元值，没标数值，<=getYAxleBaseCell()，
         *
         * @return y轴的最小单元值 就是基本单元再分的小格值
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
         * @return y轴的基本单元分成几段
         */
        public abstract int getYAxleBaseCellSegmentationNum();

        /**
         * @param position 根据position区分type组
         * @return item的组数的区分
         */
        public int getItemViewType(int position) {
            return 0;
        }

        /**
         * 每组竖状图的颜色
         *
         * @return 每组竖状图的颜色 根据type区分
         */
        @ColorRes
        public abstract int getItemColor(int type);

        /**
         * 竖状图的id
         *
         * @param position 每组item的位置
         * @return 单组item的位置
         */
        public long getItemId(int position) {
            return NO_ID;
        }

        /**
         * x轴每个单元对应的文字，文字都是一样的，选择一组即可
         *
         * @param position x轴对应的item的位置
         * @return 返回对应位置的text
         */
        public abstract String getXAxisText(int position);

        /**
         * 获取Item的宽度
         *
         * @return 每组item中的竖状图的宽度
         */
        public abstract float getItemWidth(int type);

        /**
         * 每个竖状图的高度
         *
         * @param type     根据type区分竖状图组数
         * @param position 每组的竖状图位置
         * @return 每组竖状图的高度
         */
        public abstract float getItemHigh(int type, int position);


        /**
         * 是否显示右侧轴
         *
         * @return 是否显示右侧轴
         */
        @Override
        public boolean openRightYAxle() {
            return false;
        }

        /**
         * 右侧轴的基本单元数
         *
         * @return 右侧轴的基本单元数
         */
        @Override
        public int getRightYAxleBaseCellNum() {
            return 1;
        }

        /**
         * 右侧轴的基本单元所代表的值
         *
         * @return 右侧轴的基本单元所代表的值
         */
        @Override
        public int getRightYAxleBaseCell() {
            return 1;
        }

        /**
         * @param position 右侧基本单元的位置
         * @return 右侧基本单元对应的文字
         */
        @Override
        public String getRightYAxleBaseCellText(int position) {
            return null;
        }

        /**
         * @return 右侧基本单元再分的每小段的值
         */
        @Override
        public int getRightYAxleSmallestCell() {
            return getRightYAxleBaseCell() / getRightYAxleBaseCellSegmentationNum();
        }

        /**
         * 右侧基本单元再分为几段
         *
         * @return 右侧基本单元再分为几段
         */
        @Override
        public int getRightYAxleBaseCellSegmentationNum() {
            return 1;
        }

        /**
         * 根据type将数据分组，
         *
         * @param type     item的type
         * @param position 每组item对应的位置
         * @return 返回的是每组item的每个位置的值，画点用
         */
        @Override
        public float getRightCellValue(int type, int position) {
            return 0;
        }

        /**
         * @param type item的type
         * @return 根据item的type来分别设置线条和点的颜色
         */
        @Override
        public int getRightItemColor(int type) {
            return R.color.lust;
        }

    }
}