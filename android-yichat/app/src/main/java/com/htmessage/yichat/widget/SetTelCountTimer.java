package com.htmessage.yichat.widget;


import android.os.CountDownTimer;
import android.widget.Button;

import com.htmessage.yichat.HTApp;
import com.htmessage.yichat.R;

public class SetTelCountTimer extends CountDownTimer {
    public static final int TIME_COUNT = 61000;// 时间防止从100s开始显示（以倒计时120s为例子）
    private Button btn;
    private String endStrRid;
    private OnCountTimerFinishListener listener;

    /**
     * 参数 millisInFuture 倒计时总时间（如60S，120s等） 参数 countDownInterval 渐变时间（每次倒计1s）
     * <p>
     * 参数 btn 点击的按钮(因为Button是Button子类，为了通用我的参数设置为Button）
     * <p>
     * 参数 endStrRid 倒计时结束后，按钮对应显示的文字
     */
    public SetTelCountTimer(long millisInFuture, long countDownInterval,
                            Button btn, String endStrRid) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;

        this.endStrRid = endStrRid;
    }

    /**
     * 参数上面有注释
     */
    public SetTelCountTimer(Button btn, String endStrRid) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    public SetTelCountTimer(Button btn) {
        super(TIME_COUNT, 1000);
        this.btn = btn;

        this.endStrRid = HTApp.getInstance().getString(R.string.Click_again);
    }

    public SetTelCountTimer(Button tv_varify, int normalColor, int timingColor) {
        this(tv_varify);

    }

    // 计时完毕时触发
    @Override
    public void onFinish() {
        this.cancel();
        btn.setText(endStrRid);
        btn.setEnabled(true);
        if (listener!=null){
            listener.onFinish();
        }
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setEnabled(false);
        btn.setText(String.format(HTApp.getInstance().getString(R.string.Remaining_seconds),String.valueOf((millisUntilFinished / 1000))));
        if (listener!=null){
            if ((millisUntilFinished / 1000) ==0){
                listener.onFinish();
            }
        }
    }
    public interface OnCountTimerFinishListener{
        void onFinish();
    }

    public void setListener(OnCountTimerFinishListener listener) {
        this.listener = listener;
    }
}