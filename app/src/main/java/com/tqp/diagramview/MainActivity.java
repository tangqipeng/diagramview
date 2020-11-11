package com.tqp.diagramview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tqp.diagramview.exception.DiagramException;

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
        DiagramView diagramView4 = findViewById(R.id.diagramView4);
        DiagramView diagramView5 = findViewById(R.id.diagramView5);
        DiagramView diagramView6 = findViewById(R.id.diagramView6);
        DiagramView diagramView7 = findViewById(R.id.diagramView7);
        DiagramView diagramView8 = findViewById(R.id.diagramView8);
        DiagramView diagramView9 = findViewById(R.id.diagramView9);
        DiagramView diagramView10 = findViewById(R.id.diagramView10);
        DiagramView diagramView11 = findViewById(R.id.diagramView11);
        List<List<XcellBean>> cellBeanList = new ArrayList<>();
        cellBeanList.add(getList());
        MultShuzhuangAdapter adapter = new MultShuzhuangAdapter(cellBeanList);
        diagramView.setAdapter(adapter);
        diagramView1.setAdapter(adapter);
        diagramView2.setAdapter(adapter);
        diagramView3.setAdapter(adapter);
        List<List<XcellBean>> cellBeanList1 = new ArrayList<>();
        cellBeanList1.add(getList());
        cellBeanList1.add(getUSAList());
        MultShuzhuangAdapter adapter1 = new MultShuzhuangAdapter(cellBeanList1);
        diagramView4.setAdapter(adapter1);

        List<List<XcellBean>> cellBeanList2 = new ArrayList<>();
        cellBeanList2.add(getList());
        cellBeanList2.add(getUSAList());
        cellBeanList2.add(getJapanList());
        MultiShuzhuangAdapter adapter2 = new MultiShuzhuangAdapter(cellBeanList2);
        diagramView5.setAdapter(adapter2);

        ShuzhuangAdapter adapter3 = new ShuzhuangAdapter(cellBeanList);
        diagramView6.setAdapter(adapter3);

        diagramView7.setAdapter(adapter3);

        List<List<XcellBean>> cellBeanList3 = new ArrayList<>();
        cellBeanList3.add(getList1());
        ShuzhuangOtherAdapter adapter4 = new ShuzhuangOtherAdapter(cellBeanList3);
        diagramView8.setAdapter(adapter4);
        diagramView9.setAdapter(adapter4);
        diagramView10.setAdapter(adapter4);

        List<List<XcellBean>> cellBeanList4 = new ArrayList<>();
        cellBeanList4.add(getList1());
        cellBeanList4.add(getList2());
        LineOtherAdapter adapter5 = new LineOtherAdapter(cellBeanList4);
        diagramView11.setAdapter(adapter5);
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

    private List<XcellBean> getUSAList(){
        List<XcellBean> beanList = new ArrayList<>();
        XcellBean xcellBean = new XcellBean("2004", 80);
        beanList.add(xcellBean);
        XcellBean xcellBean1 = new XcellBean("2005", 92);
        beanList.add(xcellBean1);
        XcellBean xcellBean2 = new XcellBean("2006", 110);
        beanList.add(xcellBean2);
        XcellBean xcellBean3 = new XcellBean("2007", 130);
        beanList.add(xcellBean3);
        XcellBean xcellBean4 = new XcellBean("2008", 138);
        beanList.add(xcellBean4);
        XcellBean xcellBean5 = new XcellBean("2009", 158);
        beanList.add(xcellBean5);
        return beanList;
    }

    private List<XcellBean> getJapanList(){
        List<XcellBean> beanList = new ArrayList<>();
        XcellBean xcellBean = new XcellBean("2004", 50);
        beanList.add(xcellBean);
        XcellBean xcellBean1 = new XcellBean("2005", 62);
        beanList.add(xcellBean1);
        XcellBean xcellBean2 = new XcellBean("2006", 78);
        beanList.add(xcellBean2);
        XcellBean xcellBean3 = new XcellBean("2007", 75);
        beanList.add(xcellBean3);
        XcellBean xcellBean4 = new XcellBean("2008", 86);
        beanList.add(xcellBean4);
        XcellBean xcellBean5 = new XcellBean("2009", 97);
        beanList.add(xcellBean5);
        return beanList;
    }

    private List<XcellBean> getList1(){
        List<XcellBean> beanList = new ArrayList<>();
        XcellBean xcellBean = new XcellBean("2004", 10, 10);
        beanList.add(xcellBean);
        XcellBean xcellBean1 = new XcellBean("2005", 18, 30);
        beanList.add(xcellBean1);
        XcellBean xcellBean2 = new XcellBean("2006", 26, 25);
        beanList.add(xcellBean2);
        XcellBean xcellBean3 = new XcellBean("2007", 34, 7);
        beanList.add(xcellBean3);
        XcellBean xcellBean4 = new XcellBean("2008", 68, 49);
        beanList.add(xcellBean4);
        XcellBean xcellBean5 = new XcellBean("2009", 82, 56);
        beanList.add(xcellBean5);
        return beanList;
    }

    private List<XcellBean> getList2(){
        List<XcellBean> beanList = new ArrayList<>();
        XcellBean xcellBean = new XcellBean("2004", 9, 20);
        beanList.add(xcellBean);
        XcellBean xcellBean1 = new XcellBean("2005", 11, 33);
        beanList.add(xcellBean1);
        XcellBean xcellBean2 = new XcellBean("2006", 34, 45);
        beanList.add(xcellBean2);
        XcellBean xcellBean3 = new XcellBean("2007", 26, 29);
        beanList.add(xcellBean3);
        XcellBean xcellBean4 = new XcellBean("2008", 45, 19);
        beanList.add(xcellBean4);
        XcellBean xcellBean5 = new XcellBean("2009", 76, 36);
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

    private static class MultiShuzhuangAdapter extends DiagramView.Adapter{

        private List<List<XcellBean>> mBeanList;
        private final static int china_type = 0;
        private final static int usa_type = 1;
        private final static int japan_type = 2;

        public MultiShuzhuangAdapter(List<List<XcellBean>> beanList) {
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
            }else if (type == usa_type){
                return R.color.lust;
            } else {
                return R.color.turkish_rose;
            }
        }

        @Override
        public String getXAxisText(int position) {
            return mBeanList.get(0).get(position).getNian();
        }


        @Override
        public float getItemWidth(int type) {
            if (type == china_type) {
                return 40f;
            } else if (type == usa_type){
                return 40f;
            } else {
                return 40f;
            }
        }

        @Override
        public float getItemHigh(int type, int position) {
            return mBeanList.get(type).get(position).getRenkou();
        }
    }

    private static class ShuzhuangAdapter extends DiagramView.Adapter{

        private List<List<XcellBean>> mBeanList;

        public ShuzhuangAdapter(List<List<XcellBean>> beanList) {
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
            return R.color.havelock_blue;
        }

        @Override
        public String getXAxisText(int position) {
            return mBeanList.get(0).get(position).getNian();
        }


        @Override
        public float getItemWidth(int type) {
            return 50f;
        }

        @Override
        public float getItemHigh(int type, int position) {
            return mBeanList.get(type).get(position).getRenkou();
        }

        @Override
        public boolean openRightYAxle() {
            return true;
        }


        @Override
        public int getRightYAxleBaseCellNum() {
            return 6;
        }

        @Override
        public int getRightYAxleBaseCell() {
            return 5;
        }

        @Override
        public String getRightYAxleBaseCellText(int position) {
            return getRightYAxleBaseCell() * (position + 1) +"%";
        }

        @Override
        public int getRightYAxleSmallestCell() throws DiagramException {
            return getRightYAxleBaseCell()/getRightYAxleBaseCellSegmentationNum();
        }

        @Override
        public int getRightYAxleBaseCellSegmentationNum() {
            return 5;
        }

        @Override
        public float getRightCellValue(int type, int position) {
            return mBeanList.get(type).get(position).getRate();
        }
    }

    private static class ShuzhuangOtherAdapter extends DiagramView.Adapter{

        private final List<List<XcellBean>> mBeanList;

        public ShuzhuangOtherAdapter(List<List<XcellBean>> beanList) {
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
            return 10;
        }

        @Override
        public int getYAxleBaseCell() {
            return 10;
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
            return R.color.havelock_blue;
        }

        @Override
        public String getXAxisText(int position) {
            return mBeanList.get(0).get(position).getNian();
        }


        @Override
        public float getItemWidth(int type) {
            return 50f;
        }

        @Override
        public float getItemHigh(int type, int position) {
            return mBeanList.get(type).get(position).getRenkou();
        }

        @Override
        public boolean openRightYAxle() {
            return true;
        }


        @Override
        public int getRightYAxleBaseCellNum() {
            return 6;
        }

        @Override
        public int getRightYAxleBaseCell() {
            return 10;
        }

        @Override
        public String getRightYAxleBaseCellText(int position) {
            return getRightYAxleBaseCell() * (position + 1) +"%";
        }

        @Override
        public int getRightYAxleSmallestCell() throws DiagramException {
            return getRightYAxleBaseCell()/getRightYAxleBaseCellSegmentationNum();
        }

        @Override
        public int getRightYAxleBaseCellSegmentationNum() {
            return 5;
        }

        @Override
        public float getRightCellValue(int type, int position) {
            return mBeanList.get(type).get(position).getRate();
        }

        @Override
        public int getRightItemColor(int type) {
            return super.getRightItemColor(type);
        }
    }

    private static class LineOtherAdapter extends DiagramView.Adapter{

        /**
         * XcellBean只是一个对象，这是可变的，你用你自己的，传入的参数可以是List<T>也行，
         * 只是下面的配置由你自己设置好就行。
         * 我这里是List<List<T>>，是因为我这里是多组对比
         */
        private final List<List<XcellBean>> mBeanList;
        private final static int china_type = 0;
        private final static int usa_type = 1;

        public LineOtherAdapter(List<List<XcellBean>> beanList) {
            this.mBeanList = beanList;
        }

        /**
         * 对比的组数，
         * 如果你们只需要显示一组，你传入的参数是List<T>的话，你这里返回就写1，
         * 当然你也可以List<List<T>>，那下面就不用变了
         * @return
         */
        @Override
        public int getTypeCount() {
            return mBeanList.size();
        }

        /**
         * 每组数组的个数，这里实际上是x轴的单元数
         * @return
         */
        @Override
        public int getItemCount() {
            return mBeanList.get(0).size();
        }

        /**
         * y轴上标有数值的基准长度，上图中左侧的y轴每隔几小格就标记了一个文字，
         * 这里是返回有几个这样的标有文字的单元，那些没标数值的不要算，例如图1就是5格
         * @return
         */
        @Override
        public int getYAxleBaseCellNum() {
            return 10;
        }

        /**
         * 每一标有数值的单元格所代表的数值
         * @return
         */
        @Override
        public int getYAxleBaseCell() {
            return 10;
        }

        /**
         * 每一标有数值的单元格的数字所对应的文字
         * @param position
         * @return
         */
        @Override
        public String getYAxleBaseCellText(int position) {
            return getYAxleBaseCell() * (position + 1) + "";
        }

        /**
         * 每一个基准单元格又分为几格，如果不再分割则返回1
         * @return
         */
        @Override
        public int getYAxleBaseCellSegmentationNum() {
            return 5;
        }

        /**
         * 你区分的type，实际上就是对比的每组数组的标识，
         * 这里目前我好像漏掉了没啥用，但不影响显示，以后会完善
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            return position;
        }

        /**
         * 按type区分，每一组的竖状图的颜色
         * @param type
         * @return
         */
        @Override
        public int getItemColor(int type) {
            if (type == china_type) {
                return R.color.havelock_blue;
            }else{
                return R.color.colorAccent;
            }
        }

        /**
         * x轴所标记的文字
         * @param position
         * @return
         */
        @Override
        public String getXAxisText(int position) {
            return mBeanList.get(0).get(position).getNian();
        }

        /**
         * x轴，每一组的竖状图的宽度，可以通过type来设置，如果都是一样宽，那就不需要区分
         * @param type
         * @return
         */
        @Override
        public float getItemWidth(int type) {
            return 50f;
        }

        /**
         * 按type区分，每一组的每一个x单元的竖状图的峰值，是有多高
         * @param type
         * @param position
         * @return
         */
        @Override
        public float getItemHigh(int type, int position) {
            return mBeanList.get(type).get(position).getRenkou();
        }

        /**
         * 这里是是否显示右侧的y轴，适用于画点连线的图，默认是false，
         * 这个方法不一定需要实现，如果启动右侧轴的话就实现，并返回true
         * @return
         */
        @Override
        public boolean openRightYAxle() {
            return true;
        }

        /**
         * 右侧轴的标有数值的基本单元分为几格
         * @return
         */
        @Override
        public int getRightYAxleBaseCellNum() {
            return 6;
        }

        /**
         * 右侧轴的标有数值的每一个基本单元所代表的数值
         * @return
         */
        @Override
        public int getRightYAxleBaseCell() {
            return 10;
        }

        /**
         * 右侧轴的标有数值的每一个基本单元对应的文字
         * @return
         */
        @Override
        public String getRightYAxleBaseCellText(int position) {
            return getRightYAxleBaseCell() * (position + 1) +"%";
        }

        /**
         * 右侧轴的标有数值的每一个基本单元是否再分，不分则返回1
         * @return
         */
        @Override
        public int getRightYAxleBaseCellSegmentationNum() {
            return 5;
        }

        /**
         * 右侧轴标有数值的基本单元所对应的文字
         * @param type
         * @param position
         * @return
         */
        @Override
        public float getRightCellValue(int type, int position) {
            return mBeanList.get(type).get(position).getRate();
        }

        /**
         * 线形图根据type来区分颜色，如果只有一组那就没必要区分了
         * @param type
         * @return
         */
        @Override
        public int getRightItemColor(int type) {
            if (type == china_type) {
                return R.color.lust;
            }else{
                return R.color.sunglow;
            }
        }
    }

}