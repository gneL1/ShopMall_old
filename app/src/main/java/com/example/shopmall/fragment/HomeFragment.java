package com.example.shopmall.fragment;

import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopmall.R;

import com.example.shopmall.okhttputilstest.Constans;
import com.example.shopmall.okhttputilstest.ResultBeanData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import com.alibaba.fastjson.JSON;



public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private final static String TAG = HomeFragment.class.getSimpleName();
    //getSimpleName得到类的简写名称即HomeFragment

    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView rv_home;
    private ImageView ib_top;



    @Override
    public View initView() {
        Log.e(TAG,"主界面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home,null);

        //初始化布局控件
        tv_search_home = (TextView) view.findViewById(R.id.Tv_Search_Home);
        tv_message_home = (TextView)view.findViewById(R.id.Tv_Message_Home);
        rv_home = (RecyclerView)view.findViewById(R.id.Rv_Home);
        ib_top = (ImageView) view.findViewById(R.id.Ib_Top);//或者ImageButton？

        //设置点击事件
        tv_message_home.setOnClickListener(this);
        tv_search_home.setOnClickListener(this);
        ib_top.setOnClickListener(this);

        return view;
    }

//
//    @Override
//    public void initData() {
//        super.initData();
//        Log.e(TAG,"主页面的Fragment的数据被初始化了");
//
//        //联网请求首页数据
//        getDataFromNet();
//    }


//    private void getDataFromNet(){
//        String url = Constans.HOME_URL;
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e(TAG,"首页请求失败==" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e(TAG,"首页请求成功==" + response);
//
//                        processData(response);
//                    }
//                });
//    }

//    private void processData(String json){
//
//        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
//        resultBean = resultBeanData.getResult();
//        Log.e(TAG,"解析成功=="+resultBean.getHot_info().get(0).getName());
//    }







    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Ib_Top:
                //点击图片让recyclerview滚动到顶部
                rv_home.scrollToPosition(0);
                break;
            case R.id.Tv_Search_Home:
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Tv_Message_Home:
                Toast.makeText(mContext,"进入消息中心",Toast.LENGTH_SHORT).show();
                break;
        }
    }



//    //返回的数据
//    private ResultBeanData.ResultBean resultBean;
//



}
