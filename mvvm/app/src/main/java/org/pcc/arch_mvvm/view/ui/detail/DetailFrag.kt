package org.pcc.arch_mvvm.view.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.detail_frag.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.pcc.arch_mvvm.R

class DetailFrag: Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.let {
            DetailFragArgs.fromBundle(it).url
        }

        initWebView()
        webview_back.onClick {
            webview.goBack()
        }

        webview_forward.onClick {
            webview.goForward()
        }

        webview_refresh.onClick {
            webview.reload()
        }


        webview.loadUrl(url)
    }

    private fun initWebView() {
        webview.setInitialScale(1)
        webview.settings.setAppCacheEnabled(false)
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
        webview.settings.javaScriptEnabled = true
        webview.settings.useWideViewPort = true
        webview.settings.domStorageEnabled = true

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (webview_back != null &&
                        webview_forward != null &&
                        webview != null &&
                        webview_progressbar != null) {
                    webview_back.isEnabled = webview.canGoBack()
                    webview_forward.isEnabled = webview.canGoForward()
                    webview_progressbar.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(webview_back != null &&
                        webview_forward != null &&
                        webview != null) {
                    webview_back.isEnabled = webview.canGoBack()
                    webview_forward.isEnabled = webview.canGoForward()
                    webview_progressbar.visibility = View.GONE
                }
            }
        }
    }

}