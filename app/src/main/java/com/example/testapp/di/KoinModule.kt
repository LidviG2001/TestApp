package com.example.testapp.di

import com.example.testapp.MainActivity
import com.example.testapp.MainActivitySharedViewModel
import com.example.testapp.di.viewmodel.repository.remotedatasource.firebase.FireBaseConfigurator
import com.example.testapp.utils.NetworkUtils
import org.koin.dsl.module

val appModule : org.koin.core.module.Module = module{

    single{
        MainActivitySharedViewModel()
    }

    factory<NetworkUtils>{
        NetworkUtils(get())
    }

    single<FireBaseConfigurator>{
        FireBaseConfigurator(it.get(0))
    }
}