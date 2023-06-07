package com.example.testapp.di.viewmodel.repository.remotedatasource.firebase

import android.app.Activity
import android.app.RemoteAction
import android.util.Log
import android.webkit.WebView
import com.example.testapp.di.viewmodel.repository.remotedatasource.DataLinks
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FireBaseConfigurator() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var remoteConfig: FirebaseRemoteConfig

    private var list = mutableListOf<DataLinks>()

    init {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

/*        remoteConfig.fetchAndActivate().addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                Log.d("AAA", "Config is changed")
            } else{
                Log.d("AAA", "Config is failed")
            }
            remoteConfig.getBoolean("key1")

            Log.d("AAA", "Boolean is ${remoteConfig.getBoolean("key1")}")
        }*/

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("data")

        Log.d("AAA", "Remote config is $remoteConfig")

//        webView = findViewById(R.id.webView)
//
//        webView.webViewClient = WebViewClient()
//
//        getData(webView)


    }

    fun setRemoteConfigCompleteAction(
        activity: Activity,
        action: (task: Task<*>, remoteConfig: FirebaseRemoteConfig) -> Unit
    ) {
        remoteConfig.fetchAndActivate().addOnCompleteListener(activity) { task ->
            action(task as Task<*>, remoteConfig)
        }

    }

    fun setRealTimeDatabaseAction(action: (link : String) -> Unit) {
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.e("AAA", "onDataChanger: $snapshot")

                for (ds in snapshot.children) {
                    val link = ds.child("link1").value.toString()
                    action(link)
                }
      //          Log.d("AAA", "size is ${list.get(0)}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AAA", "on Called ${error.toException()}")
            }
        })

    }
}