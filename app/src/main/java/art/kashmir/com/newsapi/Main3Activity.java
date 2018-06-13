package art.kashmir.com.newsapi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
EditText n,a;
String id;
mydb mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("To-Do");
        toolbar.setNavigationIcon(R.drawable.home);
        mydb=new mydb(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(Main3Activity.this,noteactivity.class);
               startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
                else{
                    finish();
                }
            }
        });
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bak);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
                else{
                    finish();
                    onBackPressed();
                }


            }
        });
        n=findViewById(R.id.name);
        a=findViewById(R.id.age);
        n.setText(getIntent().getStringExtra("name"));
        a.setText(getIntent().getStringExtra("age"));
        id=getIntent().getStringExtra("id");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sqlmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.up:
                String name=n.getText().toString().trim();
                String age=a.getText().toString().trim();
                boolean update;
                try {
                    update = mydb.update(id,age,name);

                if(update){
                        Toast.makeText(this, "updated succcessfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Main3Activity.this,add.class);
                    startActivity(intent);
                    finish();
                    }
                    else{
                        Toast.makeText(this, "something went wrong !try again", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete:
                boolean delete;
                try {
                    delete=mydb.delete(id);
                    if(delete){

                        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Main3Activity.this,add.class);
                    startActivity(intent);
                    finish();
                    }
                    else{
                        Toast.makeText(this, "something went wrong ", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.remi:
                Toast.makeText(this, "Reminder set", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(Main3Activity.this,MyReceiver.class);
                intent1.putExtra("head",n.getText().toString());
                intent1.putExtra("msg",a.getText().toString());
                PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent1,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                long ti= SystemClock.elapsedRealtime();
                long time=60*60*12*1000;
                long delay=ti+time;
                if (alarmManager != null) {
                    alarmManager.set(AlarmManager.ELAPSED_REALTIME,delay,pendingIntent);
                }
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
