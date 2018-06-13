package art.kashmir.com.newsapi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
TabLayout tabs;
ViewPager page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tabs=(TabLayout)findViewById(R.id.tablayout);
        page=(ViewPager)findViewById(R.id.mypage);
        tabs.setupWithViewPager(page);
        setupviewpager(page);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void setupviewpager(ViewPager viewPager){
        myviewpageradapter adapter=new myviewpageradapter(getSupportFragmentManager());

        //call the function with two values i.e fragment and text
        //adapter.AddFragment(new world(),"to-do list");
        //adapter.AddFragment(new signupfrag(),"Signup");
       // adapter.AddFragment(new kashmir(),"kashmir news");
       // adapter.AddFragment(new kashmirnews(),"kashmir news");
        //setup adapter here
        viewPager.setAdapter(adapter);
        tabs.getTabAt(0).setText("To=do");
        tabs.getTabAt(1).setText("news");
        tabs.getTabAt(2).setText("add todo");
    }
    public class myviewpageradapter extends FragmentPagerAdapter {
        private List<Fragment> myfrag=new ArrayList<>();
        private List<String>mystring=new ArrayList<>();


        public myviewpageradapter(FragmentManager fm) {
            super(fm);
        }
        public void AddFragment(Fragment frag,String string){
            myfrag.add(frag);
            mystring.add(string);
        }
        @Override
        public Fragment getItem(int position) {
            if(position==0){

            }
            return myfrag.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
