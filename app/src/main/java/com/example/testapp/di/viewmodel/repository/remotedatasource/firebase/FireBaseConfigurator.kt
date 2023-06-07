package com.example.testapp.di.viewmodel.repository.remotedatasource.firebase

import android.content.Context
import android.util.Log
import com.example.testapp.utils.getActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FireBaseConfigurator(private val context: Context) {
    private  var databaseReference: DatabaseReference
    private  var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private  var remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    init {

        databaseReference = firebaseDatabase.getReference("data")

        Log.d("AAA", "Remote config is $remoteConfig")
    }

    fun setRemoteConfigCompleteAction(
        action: (task: Task<*>, remoteConfig: FirebaseRemoteConfig) -> Unit
    ) {

        val configSettings = remoteConfigSettings {

            minimumFetchIntervalInSeconds = 10
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

        context.getActivity()?.let {
            Log.d("AAA", "Something qwerty")
            remoteConfig.fetchAndActivate().addOnCompleteListener(it) { task ->
                action(task as Task<*>, remoteConfig)
            }
        }

    }

    fun setRealTimeDatabaseAction(action: (link : String) -> Unit) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.e("AAA", "onDataChanger: $snapshot")

                for (ds in snapshot.children) {
                    val link = ds.child("link1").value.toString()
                    action(link)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AAA", "on Called ${error.toException()}")
            }
        })

    }
}
