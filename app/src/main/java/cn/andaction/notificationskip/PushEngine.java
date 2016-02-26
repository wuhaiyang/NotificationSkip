/**
 * 
 */
package cn.andaction.notificationskip;

import java.util.List;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;

/**
 * @author wuhaiyang
 *
 */
public class PushEngine {

	private static final String APP_ID = "2882303761517440802";
	private static final String APP_KEY = "5481744095802";
	protected static final String TAG = "pushtag";
	private static volatile PushEngine mInstance;
	private PushEngine() {
	}
	public static PushEngine newInstance() {
		if (null == mInstance) {
			synchronized (PushEngine.class) {
				if (null == mInstance) {
					mInstance = new PushEngine();
				}
			}
		}
		return mInstance;
	}

	public void registerPush(boolean isDebug) {
		if (shouldInit()) {
			MiPushClient.registerPush(CApplication.mGlobalContext, APP_ID, APP_KEY);
		}

		if (isDebug) {
			// 打开Log
			LoggerInterface newLogger = new LoggerInterface() {

				@Override
				public void setTag(String tag) {
					// ignore
				}

				@Override
				public void log(String content, Throwable t) {
					Log.d(TAG, content, t);
				}

				@Override
				public void log(String content) {
					Log.d(TAG, content);
				}
			};
			Logger.setLogger(CApplication.mGlobalContext, newLogger);
		}
	}
	
	
	public String getRegisterId(){
		String regId = MiPushClient.getRegId(CApplication.mGlobalContext);
		return regId;
	}
	
	public void checkManifest (){
		MiPushClient.checkManifest(CApplication.mGlobalContext);
	}

	private boolean shouldInit() {
		ActivityManager am = ((ActivityManager) CApplication.mGlobalContext.getSystemService(Context.ACTIVITY_SERVICE));
		List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = CApplication.mGlobalContext.getPackageName();
		int myPid = android.os.Process.myPid();
		for (RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}

}
