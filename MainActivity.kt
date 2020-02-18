package com.example.webview

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.webkit.WebSettings
import android.os.Build

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var SAMPLEURL = ""
        val myWebView: WebView = findViewById(R.id.web1)
        myWebView.setWebViewClient(WebViewClient())
        myWebView.settings.setSupportZoom(true)       // 줌 사용 여부 : HTML Meta태그에 적어놓은 설정이 우선 됨
        myWebView.settings.setBuiltInZoomControls(true) // 줌 사용 여부와 같이 사용해야 하는 설정(안드로이드 내장 기능)
        myWebView.settings.setDisplayZoomControls(false) // 줌 사용 시 하단에 뜨는 +, - 아이콘 보여주기 여부
        myWebView.settings.setJavaScriptEnabled(true) // 자바스크립트 사용 여부
        myWebView.settings.setDomStorageEnabled(true) // 웹뷰내의 localStorage 사용 여부
        myWebView.settings.setGeolocationEnabled(true) // 웹뷰내의 위치 정보 사용 여부

        //webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 웹뷰내의 JS의 window.open()을 허용할 것인지에 대한 여부
        if (Build.VERSION.SDK_INT >= 16) {
            myWebView.settings.setAllowFileAccessFromFileURLs(true)
            myWebView.settings.setAllowUniversalAccessFromFileURLs(true)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            myWebView.settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW) // HTTPS HTTP의 연동, 서로 호출 가능하도록
        }

        myWebView.loadUrl(SAMPLEURL)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
