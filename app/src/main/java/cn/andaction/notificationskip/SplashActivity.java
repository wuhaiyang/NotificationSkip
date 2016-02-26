package cn.andaction.notificationskip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.andaction.nslib.NSBaseActivity;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-02-26
 * Time: 11:50
 * Description: .....
 */
public class SplashActivity extends NSBaseActivity {


    private Handler mWeakHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mWeakHandler.sendEmptyMessageDelayed(0x110,2500);
    }
    private View getContentView() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        textView.setText("this is splashUI");
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        return  textView;
    }

    @Override
    protected void onStop() {
        mWeakHandler.removeCallbacksAndMessages(null);
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        mWeakHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
