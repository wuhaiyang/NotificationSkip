package cn.andaction.nslib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-02-26
 * Time: 11:21
 * Description:
 */
public class NSSkipClient {

    private static volatile NSSkipClient mInstance;
    private Intent tmpIntent  = null; //定义此变量省去了splash-Main act 过程中数据传输 增加额外的使用上成本

    private NSSkipClient (){}
    public static NSSkipClient get(){
        if (null == mInstance) {
            synchronized (NSSkipClient.class) {
                if (null == mInstance) {
                    mInstance = new NSSkipClient();
                }
            }
        }
        return mInstance;
    }
    /**
     * 通知栏消息点击事件跳转的具体业务逻辑
     * @param context
     * @param targetIntent 目标界面 携带额外参数
     */
    public void handleNotificationClickLogic(Context context,Intent targetIntent){
        if (CActivityStack.getAppManager().isStackEmpty()) {
            // 当前app已经被用户手动退出
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            launchIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
            tmpIntent = targetIntent;
        } else {
            // 当前app 处于前台
            targetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(targetIntent);
        }
    }
    /**
     * 在splash——>Main
     *  在自定义初始化完成之后 ，进行监听数据传输
     * @param activity MainActivity
     */
    public void listeneIn(Activity activity){
        if (null == activity || null == tmpIntent) return;
        activity.startActivity(tmpIntent);
        tmpIntent = null;
    }



}
