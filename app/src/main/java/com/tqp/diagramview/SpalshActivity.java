package com.tqp.diagramview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangqipeng
 * @date 12/15/20 11:54 AM
 * @email tangqipeng@aograph.com
 */
public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DiagramView diagramView = findViewById(R.id.diagramView);
        List<List<XcellBean>> cellBeanList = new ArrayList<>();
        cellBeanList.add(getList());
        MultShuzhuangAdapter adapter = new MultShuzhuangAdapter(cellBeanList);
        diagramView.setAdapter(adapter);
    }

    private List<XcellBean> getList(){
        List<XcellBean> beanList = new ArrayList<>();
        XcellBean xcellBean = new XcellBean("2004", 100, 0);
        beanList.add(xcellBean);
        XcellBean xcellBean1 = new XcellBean("2005", 120, 20);
        beanList.add(xcellBean1);
        XcellBean xcellBean2 = new XcellBean("2006", 148, 25);
        beanList.add(xcellBean2);
        XcellBean xcellBean3 = new XcellBean("2007", 160, 7);
        beanList.add(xcellBean3);
        XcellBean xcellBean4 = new XcellBean("2008", 188, 19);
        beanList.add(xcellBean4);
        XcellBean xcellBean5 = new XcellBean("2009", 220, 16);
        beanList.add(xcellBean5);
        return beanList;
    }

    private static class MultShuzhuangAdapter extends DiagramView.Adapter{

        private final List<List<XcellBean>> mBeanList;
        private final static int china_type = 0;
        private final static int usa_type = 1;

        public MultShuzhuangAdapter(List<List<XcellBean>> beanList) {
            this.mBeanList = beanList;
        }

        @Override
        public int getTypeCount() {
            return mBeanList.size();
        }

        @Override
        public int getItemCount() {
            return mBeanList.get(0).size();
        }

        @Override
        public int getYAxleBaseCellNum() {
            return 5;
        }

        @Override
        public int getYAxleBaseCell() {
            return 50;
        }

        @Override
        public String getYAxleBaseCellText(int position) {
            return getYAxleBaseCell() * (position + 1) + "";
        }

        @Override
        public int getYAxleBaseCellSegmentationNum() {
            return 5;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemColor(int type) {
            if (type == china_type) {
                return R.color.havelock_blue;
            }else{
                return R.color.lust;
            }
        }

        @Override
        public String getXAxisText(int position) {
            return mBeanList.get(0).get(position).getNian();
        }


        @Override
        public float getItemWidth(int type) {
            if (type == china_type) {
                return 50f;
            } else {
                return 45f;
            }
        }

        @Override
        public float getItemHigh(int type, int position) {
            return mBeanList.get(type).get(position).getRenkou();
        }
    }

}
