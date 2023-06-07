package com.example.testapp.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.testapp.BaseFragment
import com.example.testapp.R
import com.example.testapp.databinding.FragmentGame2Binding
import com.example.testapp.databinding.FragmentWebViewBinding
import com.example.testapp.utils.NetworkUtils
import org.koin.android.ext.android.inject


class WebViewFragment : BaseFragment() {

    private lateinit var binding : FragmentWebViewBinding

    private lateinit var webView : WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        activity?.setContentView(binding.root)
        webView = binding.webView

        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()

        Log.d("AAA", "Something happen in web")

    }

    private fun initWebView(){
        webView.webViewClient = WebViewClient()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun observeViewModel() {
        super.observeViewModel()

        mainActivitySharedViewModel.link.observe(viewLifecycleOwner){

            if(it.isEmpty() && !this::webView.isInitialized) return@observe

            webView.loadUrl(it)

            webView.settings.javaScriptEnabled = true

            webView.settings.setSupportZoom(true)
        }
    }
}