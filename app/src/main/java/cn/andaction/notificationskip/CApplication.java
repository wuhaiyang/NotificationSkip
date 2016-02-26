package cn.andaction.notificationskip;

import android.app.Application;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-02-26
 * Time: 11:50
 * Description: .....
 */
public class CApplication extends Application {
    public static CApplication mGlobalContext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mGlobalContext = this;
        PushEngine.newInstance().registerPush(true);
    }
}
