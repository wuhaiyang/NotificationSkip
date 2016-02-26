package cn.andaction.nslib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-02-26
 * Time: 13:02
 * Description: .....
 */
public class NSBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CActivityStack.getAppManager().addActivity(this);
    }
    @Override
    protected void onDestroy() {
        CActivityStack.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
