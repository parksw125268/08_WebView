package com.kotlin.a08_webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val goHomeButton: ImageButton by lazy{
        findViewById(R.id.goHomeButton)
    }
    private val goBackButton: ImageButton by lazy{
        findViewById(R.id.goBackButton)
    }
    private val goForwardButton: ImageButton by lazy{
        findViewById(R.id.goForwardButton)
    }

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

    override fun onBackPressed() {
        if(webView.canGoBack()){ //뒤로 갈 수 있는지 확인
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews(){
        webView.apply {
            webViewClient = WebViewClient()//기본 브라우저가 아니라 우리가 만든 웹뷰로 열겠다.
            settings.javaScriptEnabled = true //자바스크립트가 안드에서는 기본적으로 차단됨(보안상의 이유) 그걸 허용.
            loadUrl(DEFAULT_URL)
        }
    }
    private fun bindViews(){
        goHomeButton.setOnClickListener {
            webView.loadUrl(DEFAULT_URL)
        }
        //v: 액션이 발생한 View(주소창) , actionId : actionDone , event : 눌렀는지 땠는 지 등등..
        //반환 : true : 이벤트 다썼다. false : 아직 다 안썼고 다른데서도 써야되니 남겨놔라
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                webView.loadUrl(v.text.toString())
            }
            return@setOnEditorActionListener false //키보드 닫는 처리도 해야되므로
        }


        goBackButton.setOnClickListener {
            webView.goBack()
        }
        goForwardButton.setOnClickListener {
            webView.goForward()
        }



    }
    companion object{
        private const val DEFAULT_URL = "http://www.google.com"
    }
}