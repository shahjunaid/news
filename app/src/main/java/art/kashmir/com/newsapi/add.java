package art.kashmir.com.newsapi;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.DateInterval;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class add extends AppCompatActivity {
    android.support.v4.app.FragmentManager fragment;
    FragmentTransaction trans;
    FragmentManager fr=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.home);
        toolbar.setTitle("Notes");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               Intent intent=new Intent(add.this,Main5Activity.class);
               startActivity(intent);
            }
        });
        if(savedInstanceState != null){
            fragment = getSupportFragmentManager();
            fr.beginTransaction().replace(R.id.jack,new world()).commit();
        }
        else{
            fragment = getSupportFragmentManager();
            fr.beginTransaction().replace(R.id.jack,new world()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.abc,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
               Intent intent=new Intent(add.this,noteactivity.class);
               startActivity(intent);
               finish();
               break;
        }
        return super.onOptionsItemSelected(item);
    }
}
