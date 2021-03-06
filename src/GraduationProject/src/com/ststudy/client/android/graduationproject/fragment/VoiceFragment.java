package com.ststudy.client.android.graduationproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.adapter.SplashAdapter;
import com.ststudy.client.android.graduationproject.listener.OnViewPagerSelectedLastListener;
import com.ststudy.client.android.ui.pagerslidingtabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/1/18.
 * 音乐主界面
 */
public class VoiceFragment extends BaseFragment {


    private PagerSlidingTabStrip mSlidingTabVoice;
    private ViewPager mVp4Voice;

    @SuppressLint("ValidFragment")
    private VoiceFragment() {
    }

    public static VoiceFragment getInstance() {
        return VoiceFragmentHolder.INSTANCE;
    }

    private static class VoiceFragmentHolder {
        public static final VoiceFragment INSTANCE = new VoiceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_voice, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
        bindData();
        setListener();
    }

    /**
     * 初始化界面
     *
     * @param pView 当前显示的View
     */
    @Override
    protected void initView(View pView) {
        mSlidingTabVoice = (PagerSlidingTabStrip) pView.findViewById(R.id.slidingTabVoice);
        mVp4Voice = (ViewPager) pView.findViewById(R.id.vp4Voice);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mSlidingTabVoice.getTabCount(); i++) {
            list.add(R.drawable.ic_launcher);
        }
        SplashAdapter mVoiceAdapter = new SplashAdapter(getActivity(), list);
        mVp4Voice.setAdapter(mVoiceAdapter);
    }

    /**
     * 绑定数据
     */
    @Override
    protected void bindData() {
        mSlidingTabVoice.setViewPager(mVp4Voice);
    }

    /**
     * 设置组件监听
     */
    @Override
    protected void setListener() {
        mVp4Voice.addOnPageChangeListener(new OnViewPagerSelectedLastListener() {
            @Override
            public void onPageSelected(int i) {

            }
        });
    }

}
