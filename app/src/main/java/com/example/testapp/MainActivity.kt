package com.example.testapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.databinding.FragmentGameBinding
import com.example.testapp.di.viewmodel.repository.remotedatasource.firebase.FireBaseConfigurator



class MainActivity : AppCompatActivity() {

    enum class Turn{
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.NOUGHT

    private var crossesScore = 0
    private var noughtsScore = 0

    private lateinit var binding : ActivityMainBinding

    private var boardList = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fireBaseConfigurator = FireBaseConfigurator()

        fireBaseConfigurator.setRemoteConfigCompleteAction(this,) {task , remoteConfig ->
            if(task.isSuccessful){
                Log.d("AAA", "Config is changed")
            } else{
                Log.d("AAA", "Config is failed")
            }
            remoteConfig.getBoolean("key1")
            Log.d("AAA", "boolean ${remoteConfig.getBoolean("key1")}")

            if(remoteConfig.getBoolean("key1")){
                fireBaseConfigurator.setRealTimeDatabaseAction {
                    val bundle = Bundle().putString("My arg", it)
                    val nav_host_fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                    val navController = nav_host_fragment.navController
                    navController.navigate(R.id.WebViewFragment)
                    Log.d("AAA", "Link is $it")
                 //   binding.root.findNavController().navigate(R.id.WebView)
                }

            }
            else{

            }
        }


    }


}
