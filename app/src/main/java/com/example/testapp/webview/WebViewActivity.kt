package com.example.testapp.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.testapp.R
import com.example.testapp.di.viewmodel.repository.remotedatasource.DataLinks
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class WebViewActivity : AppCompatActivity() {

    private lateinit var databaseReference : DatabaseReference
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var remoteConfig : FirebaseRemoteConfig

    private var list = mutableListOf<DataLinks>()

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate().addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                Log.d("AAA", "Config is changed")
            } else{
                Log.d("AAA", "Config is failed")
            }
            remoteConfig.getBoolean("key1")

            Log.d("AAA", "Boolean is ${remoteConfig.getBoolean("key1")}")
        }

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("data")

        Log.d("AAA", "Remote config is $remoteConfig")

        webView = findViewById(R.id.webView)

        webView.webViewClient = WebViewClient()

        getData(webView)

    }

    private fun getData(webView : WebView){
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.e("AAA","onDataChanger: $snapshot")

                for(ds in snapshot.children){
                    val id = ds.key.toString()
                    val link = ds.child("link1").value.toString()

                    val dataLinks = DataLinks(id, link)

                    webView.loadUrl(link)

                    webView.settings.javaScriptEnabled = true

                    webView.settings.setSupportZoom(true)

                    Log.d("AAA", "link is $dataLinks")

                    list.add(dataLinks)

                    Log.d("AAA", "in list ${list.get(0)}")
                }
                Log.d("AAA", "size is ${list.get(0)}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AAA", "on Called ${error.toException()}")
            }
        })

    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            onBackPressedDispatcher.onBackPressed()

    }
}