package art.kashmir.com.newsapi;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Main5Activity extends AppCompatActivity {
    Toolbar toolbar;
    android.support.v4.app.FragmentManager fragment;
    FragmentTransaction trans;
    int fragmentno;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FragmentManager fr=getFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fr.beginTransaction().replace(R.id.frame,new kashmir()).commit();
                    toolbar.setTitle("Trending");
                    fragmentno=1;
                    return true;
                case R.id.navigation_dashboard:
                    fr.beginTransaction().replace(R.id.frame,new kashmirnews()).commit();
                    toolbar.setTitle("Tech news");
                    fragmentno=2;
                    return true;
                case R.id.navigation_notifications:
                    fr.beginTransaction().replace(R.id.frame,new scientific()).commit();
                    toolbar.setTitle("Science");
                    fragmentno=3;
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        fragment = getSupportFragmentManager();

        toolbar=findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(10);
        }
        if(savedInstanceState != null){
           int a=savedInstanceState.getInt("fragment");
           if(a==1){

               fr.beginTransaction().replace(R.id.frame,new kashmir()).commit();
               toolbar.setTitle("Trending");
           }
           if(a ==2){

               fr.beginTransaction().replace(R.id.frame,new kashmirnews()).commit();
               toolbar.setTitle("Tech news");
           }
           if(a ==3){
               fr.beginTransaction().replace(R.id.frame,new scientific()).commit();
               toolbar.setTitle("Science");
           }
        }
        else{
            fr.beginTransaction().replace(R.id.frame,new kashmir()).commit();
            toolbar.setTitle("Trending");
        }
    setSupportActionBar(toolbar);
        BottomNavigationView navigation =findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(this);
            Slide slide=new Slide();
            slide.setSlideEdge(Gravity.RIGHT);
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(this);
                    Slide slide=new Slide();
                    slide.setSlideEdge(Gravity.LEFT);
                    slide.setDuration(1000);
                    getWindow().setExitTransition(slide);
                }

                Intent intent=new Intent(Main5Activity.this,add.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("fragment",fragmentno);
        super.onSaveInstanceState(outState);
    }
}
