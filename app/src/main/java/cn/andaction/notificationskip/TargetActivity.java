package cn.andaction.notificationskip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.andaction.nslib.NSBaseActivity;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-02-26
 * Time: 11:49
 * Description: .....
 */
public class TargetActivity extends NSBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (null != bundle) {
            Log.w("TAG",bundle.getString("testData"));
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(this,"重新刷新界面",Toast.LENGTH_SHORT).show();
        super.onNewIntent(intent);
    }
    private View getContentView() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        textView.setText("this is TargetActivity");
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        return  textView;
    }
}
