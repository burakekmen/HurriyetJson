package kodluyoruz.com.hurriyetjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetayActivity extends AppCompatActivity {
WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);


        webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        //Gelen Linki alıp WebView'da gösteriyorum

        Bundle bundle=getIntent().getExtras();

        webView.loadUrl(bundle.getString("link"));



    }
}
