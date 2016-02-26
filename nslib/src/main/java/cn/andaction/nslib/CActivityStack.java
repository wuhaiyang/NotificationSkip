package cn.andaction.nslib;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by wuhaiyang on 2015/9/10.
 */
public class CActivityStack {
    private static volatile Stack<Activity> activityStack;
    private static CActivityStack instance;

    private CActivityStack() {
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
    }
    /**
     * 单一实例
     */
    public static CActivityStack getAppManager() {
        if (instance == null) {
            synchronized (CActivityStack.class)  {
                if (null == instance) {
                    instance = new CActivityStack();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (!isExitActivity(activity)) {
            activityStack.add(activity);
        }
    }

    public boolean isExitActivity(Activity activity) {
        for (Activity tmp : activityStack) {
            if (tmp.getClass().equals(activity.getClass())) {
                return true;
            }
        }
        return false;
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }
    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public boolean isStackEmpty(){
        if (null == activityStack) {
            return true;
        }
        return activityStack.isEmpty();
    }

    /**
     * 销毁之前的界面
     * @param activity
     */
    public void destroyActivityOfStackTop(Activity activity){
        String simpleClassName = activity.getClass().getSimpleName();
        if (!isExitActivity(activity) && currentActivity().getClass().getSimpleName().equals(simpleClassName)) {
            return;
        }
        int index = 0;
        for (int i = 0 ; i < activityStack.size() ; i ++) {
            if (activityStack.get(i).getClass().getSimpleName().equals(simpleClassName)) {
                index = i;
                break;
            }
        }
        for (int i = index + 1; i < activityStack.size() ; i ++) {
            finishActivity(activityStack.get(i));
        }
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }
}
