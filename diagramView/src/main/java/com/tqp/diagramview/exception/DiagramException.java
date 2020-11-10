package com.tqp.diagramview.exception;

/**
 * @author tangqipeng
 * @date 2020/11/9 6:28 PM
 * @email tangqipeng@aograph.com
 */
public class DiagramException extends Exception {

    private String msg;

    public DiagramException(String msg) {
        super(msg);
        this.msg = msg;
    }


}
