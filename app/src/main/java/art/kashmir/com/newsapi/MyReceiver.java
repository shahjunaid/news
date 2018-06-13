package art.kashmir.com.newsapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
public static final String name="head";
public static final String value="msg";
    @Override
    public void onReceive(Context context, Intent intent) {
        String nam=intent.getStringExtra(name);
        String msg=intent.getStringExtra(value);
        notification.notify(context,nam,0,msg);
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

    }
}
