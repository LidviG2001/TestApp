package com.example.testapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.di.viewmodel.repository.remotedatasource.firebase.FireBaseConfigurator
import com.example.testapp.enum.Destinations
import com.example.testapp.utils.NetworkUtils
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity(), KoinComponent {

    private companion object {
        const val FIREBASE_CONFIG_KEY = "config_show_web_view"
    }

    private val networkUtils : NetworkUtils by inject()
    private val fireBaseConfigurator : FireBaseConfigurator by inject{parametersOf(this)}

    private val mainActivitySharedViewModel : MainActivitySharedViewModel by inject()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(networkUtils.isConnected()){
            fireBaseConfigurator.setRemoteConfigCompleteAction() {task , remoteConfig ->
                val showWebView = remoteConfig.getBoolean(FIREBASE_CONFIG_KEY)

                Log.d("AAA", "boolean is $showWebView")

                if(showWebView){
                    fireBaseConfigurator.setRealTimeDatabaseAction {

                        mainActivitySharedViewModel.setLink(it)

                        mainActivitySharedViewModel.setChangeSplash(Destinations.WEBVIEW)

                        Log.d("AAA", "Nav is complite")
                    }
                }
                else{
                    mainActivitySharedViewModel.setChangeSplash(Destinations.GAME)
                }
            }
        }else{
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
        }
    }
}
