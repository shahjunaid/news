package art.kashmir.com.newsapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class noteactivity extends AppCompatActivity {
EditText name,age;
mydb mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
name=findViewById(R.id.subject);
age=findViewById(R.id.head);
mydb=new mydb(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                String a = name.getText().toString();
                String b = age.getText().toString();
                Boolean diditwork = true;
                if(!TextUtils.isEmpty(a)) {
                    if (!TextUtils.isEmpty(b)) {

                        try {
                            mydb db = new mydb(this);
                            Boolean did = db.getdata(a, b);
                            if (did) {
                                Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(noteactivity.this, add.class);
                                startActivity(intent);
                                finish();
                            } else {
                                name.setError("error");
                                name.requestFocus();
                                Toast.makeText(this, "unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                break;
            case R.id.cancel:
                Intent intent=new Intent(noteactivity.this,add.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent=new Intent(noteactivity.this,add.class);
        startActivity(intent);
    }
}
