package com.tqp.diagramview;

/**
 * @author tangqipeng
 * @date 2020/11/9 5:41 PM
 * @email tangqipeng@aograph.com
 */
public class XcellBean {
    private String nian;
    private float renkou;
    private float rate;

    public XcellBean(String nian, float renkou) {
        this.nian = nian;
        this.renkou = renkou;
    }

    public XcellBean(String nian, float renkou, float rate) {
        this.nian = nian;
        this.renkou = renkou;
        this.rate = rate;
    }

    public String getNian() {
        return nian;
    }

    public void setNian(String nian) {
        this.nian = nian;
    }

    public float getRenkou() {
        return renkou;
    }

    public void setRenkou(float renkou) {
        this.renkou = renkou;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
