Android-NotificationSkip——Just Kiss Principle
==

## Pro background 

**读者平时项目涉及到推送的时候，考虑到开发成本等各个技术原因，基本上用的都是第三方现有的sdk。本lib针对的是：收到通知消息后，用户点击通知栏跳转到目标界面以及其他后续动作，始终会处理这块的跳转业务逻辑，心中会产生无数个翻腾.**  

---

## Actual Scene


 例如：读者某一个项目使用了小米推送服务，当receiver中的onNotificationMessageClicked(params) 方法（即是用户点击通知栏进行界面跳转的时候）调用的时候，app本身进程有两种情况：1、app正在运行；2、app已经退出。对于第一种情况	，直接将参数传入Intent并打开对应的activity即可；但是对于第二种相对而言就复杂了许多了，如果app已经退出，而要打开的目标activity中的某些操作是需要依赖app初始化的，这些初始化操作是在app启动过程中进行的,并且如果直接跳转到目标界面，当用户按了返回键之后，由于activity任务栈只存在一个，就会退出app，然后真正想要的效果是：返回到上一个界面。


## Some Tips 

   

 - 对于判断app本身是处于前台或者后台，泡网上的日子一哥们已经开源出来一套比较牛逼的方法了，链接地址忘了....，本lib是采用的是自己维护一个Stack来管理所有的activity，目的是兼容性相对而言要好一些，并且到时候对一些方法做额外开放！


 
---


## Use Code


    前台所有activity集成NSBaseActivity ---------------------->设计不是很合理 见谅了

    在你处理跳转业务方法的地方加上
    NSSkipClient.get().handleNotificationClickLogic(context, targetIntent);
    其中targetIntent不需要在去设置额外flag，你仅仅需要添加一些你想要携带的参数
     
    在MainActivity onCreate中添加   NSSkipClient.get().listeneIn(this);   ps:MainActivity 作为一个common bus 呵呵~~

    在TargetActivity onCreate中数据获取判断，onNewIntent()及时刷新界面

    TargetActivity 在功能清单文件中声明启动模式为singleTask .just recommend
 

## Other
  
   读者认为 写得tm太菜了  就一时无聊 ！！ 越看真心不够优雅

   其他问题可以通过邮件互相沟通 互相学习 wuhaiyang@andthink.cn 

   



