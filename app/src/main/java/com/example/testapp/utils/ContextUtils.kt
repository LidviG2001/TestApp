package com.example.testapp.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.MainActivity

fun Context.getActivity(): Activity? {
    Log.d("getActivity", "${this}")
    return when (this) {
        is MainActivity -> this as Activity
        is AppCompatActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        is ContextThemeWrapper -> (this as ContextThemeWrapper).baseContext.getActivity()
        is Activity -> this
        else -> null
    }
}