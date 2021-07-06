package app.spotravel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import app.spotravel.R;
import app.spotravel.api.ApiClient;
import app.spotravel.api.ApiInterface;
import app.spotravel.models.Track;
import app.spotravel.web.WebHelper;
import app.spotravel.web.WebViewClientImpl;
import retrofit2.Call;

public class GeniusLyrics extends AppCompatActivity {

    private WebView myWebView;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_genius_lyrics);

        Intent intent = getIntent();
        String trackId = intent.getStringExtra("trackId");
        String token = intent.getStringExtra("token");

        Track track = getTrack(trackId, token);

        if (track != null) {
            myWebView = (WebView) findViewById(R.id.webview);

            WebViewClientImpl webViewClient = new WebViewClientImpl(this);
            myWebView.setWebViewClient(webViewClient);

            myWebView.loadUrl(WebHelper.getUrl(track));

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}