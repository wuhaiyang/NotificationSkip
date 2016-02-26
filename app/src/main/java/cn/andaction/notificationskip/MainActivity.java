package cn.andaction.notificationskip;

import android.os.Bundle;

import cn.andaction.nslib.NSBaseActivity;
import cn.andaction.nslib.NSSkipClient;

public class MainActivity extends NSBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NSSkipClient.get().listeneIn(this);

        PushEngine.newInstance().checkManifest();
        PushEngine.newInstance().getRegisterId();
    }
}
