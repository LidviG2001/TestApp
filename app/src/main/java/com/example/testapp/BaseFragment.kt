package com.example.testapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {
    protected val mainActivitySharedViewModel : MainActivitySharedViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    protected open fun observeViewModel(){

    }
}