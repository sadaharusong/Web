package com.example.web;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private String url = "http://www.imooc.com";
	private WebView webView;
	private ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Uri uri = Uri.parse(url);
		//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
		//startActivity(intent);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		webView = (WebView)findViewById(R.id.WebView);
		//webView.loadUrl("file:///android_asset");  加载的本地资源
		webView.loadUrl("http://2014.qq.com");
		//覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，是的网页可以在WebView中打开
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				//返回值是true的时候控制网页在WebView中去打开
				//如果为false调用系统浏览器或者第三方浏览器打开
				view.loadUrl(url);
				return true;
			}
		
		});
		
		//启用支持JavaScript
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		//WebView加载页面优先使用缓存加载
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		
		
		
		webView.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				if(newProgress==100)
				{
					//网站加载完毕,关闭进度条
					closeDialog();
				}
				else {
					//正在加载，打开进度条
					openDialog(newProgress);
				}
				super.onProgressChanged(view, newProgress);
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
				if(dialog != null && dialog.isShowing())
				{
					dialog.dismiss();
					dialog = null;
				}
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if(dialog == null)
				{
					dialog = new ProgressDialog(MainActivity.this);
					dialog.setTitle("正在加载");
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.show();
					
				}
				else {
					dialog.setProgress(newProgress);
				}
			}
			
			
		});
		
		
	}
	
	
	//改写物理按键--返回的逻辑
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			if(webView.canGoBack())
			{
				webView.goBack();
				return true;
			}
			else {
				System.exit(0);
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
