package com.kotlin.a08_webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val webView : WebView by lazy{
        findViewById(R.id.webView)
    }
    private val addressBar : EditText by lazy{
        findViewById(R.id.addressBar)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews(){
        webView.apply {
            webViewClient = WebViewClient()//기본 브라우저가 아니라 우리가 만든 웹뷰로 열겠다.
            settings.javaScriptEnabled = true //자바스크립트가 안드에서는 기본적으로 차단됨(보안상의 이유) 그걸 허용.
            loadUrl("http://www.google.co.kr")
        }
    }

    private fun bindViews(){
        //v: 액션이 발생한 View(주소창) , actionId : actionDone , event : 눌렀는지 땠는 지 등등..
        //반환 : true : 이벤트 다썼다. false : 아직 다 안썼고 다른데서도 써야되니 남겨놔라
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                webView.loadUrl(v.text.toString())
            }
            return@setOnEditorActionListener true //키보드 닫는 처리도 해야되므로
        }
    }
}