package com.example.testapp.di.viewmodel.repository.remotedatasource

import com.example.testapp.di.viewmodel.repository.remotedatasource.firebase.FireBaseConfigurator

interface RemoteDataSource {



    fun getFirebaseConfigurator(fireBaseConfigurator: FireBaseConfigurator) : FireBaseConfigurator
}