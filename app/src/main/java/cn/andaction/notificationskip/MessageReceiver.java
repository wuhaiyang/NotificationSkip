package cn.andaction.notificationskip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import cn.andaction.nslib.NSSkipClient;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-02-26
 * Time: 11:52
 * Description: .....
 */
public class MessageReceiver extends PushMessageReceiver {

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
        Intent targetIntent = new Intent(context, TargetActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("testData", "testData");
        targetIntent.putExtra("bundle", bundle);
        NSSkipClient.get().handleNotificationClickLogic(context, targetIntent);
    }

    @Override
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceiveMessage(context, miPushMessage);
    }
}
