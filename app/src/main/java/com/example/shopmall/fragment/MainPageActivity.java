package com.example.shopmall.fragment;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shopmall.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainPageActivity extends AppCompatActivity {
    @Bind(R.id.Rb_Home)
    RadioButton RbHome;
    @Bind(R.id.Rb_Type)
    RadioButton RbType;
    @Bind(R.id.Rb_Community)
    RadioButton RbCommunity;
    @Bind(R.id.Rb_Cart)
    RadioButton RbCart;
    @Bind(R.id.Rb_User)
    RadioButton RbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;

    //装fragment的实例集合
    private ArrayList<BaseFragment> fragments;

    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;

    private int position = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);



        //初始化Fragment
        initFragment();

        //设置RadioGroup监听事件
        initListener();

        //把默认界面设置为HomaPage
        Fragment baseFragment = fragments.get(0);

        //记得把tempFragment初始化，不然会重叠
        //参考switchFragment方法，不初始化 第一个参数为0而不是HomePage
        //跳转过去后HomePage未被隐藏
        tempFragment = baseFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,baseFragment);
        transaction.commit();


        /**以下方式仅适用android.app包,上面的是android.support.v4.app包  方法基本一样
         *
         * Fragment baseFragment = fragment.get(0);
         *
         *  拿到Fragment的manager对象
         * FragmentManager manager = getFragmentManager();
         *
         * 事务（防止花屏）
         * FtagmentTransaction transaction = manager.beginTransaction();
         *
         * 表示使用baseFragment去替换Fragment
         * transaction.replace(R.id.frameLayout,baseFragment);
         *
         * 提交事务
         * transaction.commit();
         *
         */


    }


    private void initListener() {
        //使用RadioGroup.check()会调用onCheckedChanged()方法三次
        //使用radioBotton.setChecked()方法来解决
        RbHome.setChecked(true);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Rb_Home:
                        position = 0;
                        break;
                    case R.id.Rb_Type:
                        position = 1;
                        break;
                    case R.id.Rb_Community:
                        position = 2;
                        break;
                    case R.id.Rb_Cart:
                        position = 3;
                        break;
                    case R.id.Rb_User:
                        position = 4;
                        break;
                }
                //根据位置得到相应的Fragment
                BaseFragment baseFragment = getFragment(position);

                //第一个参数是上次显示的Fragment,第二个参数是当前正要现实的Fragment
                switchFragment(tempFragment, baseFragment);
            }
        });

    }


    private void initFragment() {
        fragments = new ArrayList<>();//注意fragments是泛型BaseFragment，所以下面的增加的类要继承BaseFragment
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new CartFragment());
        fragments.add(new UserFragment());
    }


    //根据位置得到的对应的Fragment
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }


    //切换Fragment
    private void switchFragment(Fragment fragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()) {
                    //隐藏当前的Fragment
                    if (fragment != null) {
                        transaction.hide(fragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前的Fragment
                    if (fragment != null) {
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }

    }


}
