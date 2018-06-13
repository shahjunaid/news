package art.kashmir.com.newsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class webview extends AppCompatActivity {
@BindView(R.id.webView) WebView webView;
@BindView(R.id.pro) ProgressBar pro;
@BindView(R.id.lin) LinearLayout lin;
private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
url=getIntent().getStringExtra("web");
String urls=url.replace("//s+","");
        pro.setMax(100);
        webView.setWebViewClient(new Helpclient());
        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int progress){
                pro.setProgress(progress);
                setTitle("Loading...");
                if(progress == 100){
                    lin.setVisibility(View.GONE);
                    setTitle("web");
                }
                super.onProgressChanged(view,progress);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setSaveEnabled(false);
        webView.loadUrl(getIntent().getStringExtra("web"));
        pro.setProgress(0);

    }

    private class Helpclient extends WebViewClient {
    }

}
