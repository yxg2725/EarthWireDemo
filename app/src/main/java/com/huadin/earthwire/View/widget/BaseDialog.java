package com.huadin.earthwire.View.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import com.huadin.earthwire.R;

import butterknife.ButterKnife;


/**
 * 自定义的dialog  设置显示和隐藏动画  设置显示的位置和宽度
 */
public abstract class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        this(context, R.style.BottomDialog);//设置下dialog展示和消失的动画主题
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setGravity(Gravity.TOP);//设置下dialog的位置
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(getLayoutId());//设置下dialog的view
        ButterKnife.bind(this);

        //宽度全屏
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = width;
        getWindow().setAttributes(attributes);

    }

    public abstract int getLayoutId();
}
