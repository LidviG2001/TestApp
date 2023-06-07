package com.example.testapp.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testapp.BaseFragment
import com.example.testapp.R
import com.example.testapp.databinding.FragmentSplashScreenBinding
import com.example.testapp.enum.Destinations


class SplashScreen : BaseFragment() {

    private lateinit var binding : FragmentSplashScreenBinding


    var navController: NavController?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        activity?.setContentView(binding.root)

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

    }

     override fun observeViewModel(){

        mainActivitySharedViewModel.changeSplash.observe(viewLifecycleOwner) {

            Log.d("AAA", "Is it work? $it")

            if(it != Destinations.SPLASH.name){
                when (it){
                    Destinations.WEBVIEW.name ->{
                        Log.d("AAA", "Are you sure?")
                        navController?.navigate(R.id.action_Splash_to_WebViewFragment2)
                    }
                    Destinations.GAME.name ->{
                        navController?.navigate(R.id.Splash_to_game)
                    }
                    else ->{

                    }
                }
            }
        }
    }
}