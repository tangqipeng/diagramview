package com.tqp.diagramview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiagramView diagramView = findViewById(R.id.diagramView);
        DiagramView diagramView1 = findViewById(R.id.diagramView1);
        DiagramView diagramView2 = findViewById(R.id.diagramView2);
        DiagramView diagramView3 = findViewById(R.id.diagramView3);
        ShuzhuangAdapter adapter = new ShuzhuangAdapter(getList());
        diagramView.setAdapter(adapter);
        diagramView1.setAdapter(adapter);
        diagramView2.setAdapter(adapter);
        diagramView3.setAdapter(adapter);

    }

    private List<XcellBean> getList(){
        List<XcellBean> beanList = new ArrayList<>();
        XcellBean xcellBean = new XcellBean("2004", 100);
        beanList.add(xcellBean);
        XcellBean xcellBean1 = new XcellBean("2005", 120);
        beanList.add(xcellBean1);
        XcellBean xcellBean2 = new XcellBean("2006", 148);
        beanList.add(xcellBean2);
        XcellBean xcellBean3 = new XcellBean("2007", 160);
        beanList.add(xcellBean3);
        XcellBean xcellBean4 = new XcellBean("2008", 188);
        beanList.add(xcellBean4);
        XcellBean xcellBean5 = new XcellBean("2009", 220);
        beanList.add(xcellBean5);
        return beanList;
    }

    private class ShuzhuangAdapter extends DiagramView.Adapter{

        private List<XcellBean> mBeanList;

        public ShuzhuangAdapter(List<XcellBean> beanList) {
            this.mBeanList = beanList;
        }

        @Override
        public int getAxleXColor() {
            return R.color.mystic;
        }

        @Override
        public int getAxleYColor() {
            return R.color.mystic;
        }

        @Override
        public int getItemCount() {
            return mBeanList.size();
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
            return 50 * (position + 1) + "";
        }

        @Override
        public int getYAxleBaseCellSegmentationNum() {
            return 5;
        }

        @Override
        public int getItemColor() {
            return R.color.havelock_blue;
        }

        @Override
        public String getXAxisText(int position) {
            return mBeanList.get(position).getNian();
        }

        @Override
        public float getItemWidth() {
            return 50f;
        }

        @Override
        public float getItemHigh(int position) {
            return mBeanList.get(position).getRenkou();
        }
    }

}