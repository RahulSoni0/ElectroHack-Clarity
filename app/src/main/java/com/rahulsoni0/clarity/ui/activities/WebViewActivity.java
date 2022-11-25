package com.rahulsoni0.clarity.ui.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.rahulsoni0.clarity.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    private String url;
    private String bookUrl;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            url = bundle.getString("url");
            from = bundle.getString("from");
        }
        if (from.equals("article")) {
            binding.tvTitle.setText("Article details");
        } else if (from.equals("books")) {
            String path = "";
            path += url;
            bookUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + path;
            url = bookUrl;
            binding.tvTitle.setText("book details");
        } else if (from.equals("podcasts")) {
            binding.tvTitle.setText("Podcast details");
        }
        setupWebView();
//        binding.btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(WebViewActivity.this, MainActivity.class);
//                i.putExtra("message_key", "str");
//                startActivity(i);
//                finish();
//            }
//        });

    }

    private void setupWebView() {

        binding.webViewDetails.getSettings().setJavaScriptEnabled(true);
        binding.webViewDetails.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        binding.webViewDetails.setWebViewClient(new WebViewClient() {

            ProgressBar dialog = new ProgressBar(WebViewActivity.this);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        //enable responsive layout

        binding.webViewDetails.getSettings().setLoadWithOverviewMode(true);
        binding.webViewDetails.getSettings().setSupportZoom(true);
        binding.webViewDetails.getSettings().setBuiltInZoomControls(true);
        binding.webViewDetails.getSettings().setDisplayZoomControls(true);


        binding.webViewDetails.loadUrl(url);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK) && binding.webViewDetails.canGoBack()) {
            binding.webViewDetails.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }

}