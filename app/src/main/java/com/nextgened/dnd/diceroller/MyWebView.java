package com.nextgened.dnd.diceroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWebView extends Activity {
    @BindView(R.id.myWebView)
    WebView myWebView;
    Date dateCreated = new Date();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getName(), "MyWebView Destroyed");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(this.getClass().getName(), "MyWebView Detached");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(this.getClass().getName(), "MyWebView sent a new Intent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this.getClass().getName(), "MyWebView Paused");
        File file = new File(System.getProperty("java.io.tmpdir") + "/foo.data.tmp");
        Log.i(this.getClass().getName(), "Saving File Path: " +file.getAbsolutePath());
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println(dateCreated);
            out.flush();
            out.close();
            Log.i(this.getClass().getName(), "Saved Data in: " +file.getAbsolutePath());
        } catch (IOException e) {
            Log.e(this.getClass().getName(), e.getMessage());
            Log.e(this.getClass().getName(), "Saved Data FAILED: " +file.getAbsolutePath());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(this.getClass().getName(), "MyWebView Resumed");
        File file = new File(System.getProperty("java.io.tmpdir") + "/foo.data.tmp");
        Log.i(this.getClass().getName(), "Restoring File Path: " +file.getAbsolutePath());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = reader.readLine()) != null) {
                Log.i(this.getClass().getName(), "Data READ: " + s);
            }
            reader.close();
        } catch (IOException e) {
            Log.e(this.getClass().getName(), "Read Data FAILED: " +file.getAbsolutePath());
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(this.getClass().getName(), "MyWebView Restarted");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getName(), "MyWebView Created");
        setContentView(R.layout.activity_my_web_view);
        ButterKnife.bind(this);

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.loadData(
//                "<h1>Hello, World!</h1><textarea cols='80' rows='10'>" + dateCreated + "</textarea>",
//                "text/html", "UTF-8");
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("/data/user/0/com.nextgened.dnd.diceroller/files/index.html"));
            String line, content = "";
            while ((line = reader.readLine()) != null) {
                content += line;
            }
            myWebView.loadData(
            content + "<textarea cols='80' rows='10'>" + dateCreated + "</textarea>",
            "text/html", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //myWebView.loadUrl("http://www.google.com");
    }

}
