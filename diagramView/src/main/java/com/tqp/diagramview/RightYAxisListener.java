package com.tqp.diagramview;

import android.graphics.Canvas;

import com.tqp.diagramview.exception.DiagramException;

/**
 * @author tangqipeng
 * @date 2020/11/10 6:36 PM
 * @email tangqipeng@aograph.com
 */
public interface RightYAxisListener {

    boolean openRightYAxle();

    int getRightYAxleBaseCellNum();

    int getRightYAxleBaseCell();

    String getRightYAxleBaseCellText(int position);

    int getRightYAxleSmallestCell() throws DiagramException;

    int getRightYAxleBaseCellSegmentationNum();

    float getRightCellValue(int type, int position);

    void drawCell(Canvas canvas);

}
