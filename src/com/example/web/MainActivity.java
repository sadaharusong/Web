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
		//webView.loadUrl("file:///android_asset");  ���صı�����Դ
		webView.loadUrl("http://2014.qq.com");
		//����WebViewĬ��ͨ��������������ϵͳ���������ҳ����Ϊ���ǵ���ҳ������WebView�д�
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				//����ֵ��true��ʱ�������ҳ��WebView��ȥ��
				//���Ϊfalse����ϵͳ��������ߵ������������
				view.loadUrl(url);
				return true;
			}
		
		});
		
		//����֧��JavaScript
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		//WebView����ҳ������ʹ�û������
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		
		
		
		webView.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				if(newProgress==100)
				{
					//��վ�������,�رս�����
					closeDialog();
				}
				else {
					//���ڼ��أ��򿪽�����
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
					dialog.setTitle("���ڼ���");
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
	
	
	//��д������--���ص��߼�
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
