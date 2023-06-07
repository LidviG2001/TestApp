package com.example.testapp.di.viewmodel.repository.remotedatasource

import com.example.testapp.di.viewmodel.repository.remotedatasource.firebase.FireBaseConfigurator
import javax.inject.Inject

class RemoteDataSourceImpl(val fireBaseConfigurator: FireBaseConfigurator) : RemoteDataSource{

    /*@Inject
    val firebaseConfigurator = FireBaseConfigurator()*/

    override fun getFirebaseConfigurator(fireBaseConfigurator: FireBaseConfigurator): FireBaseConfigurator {
        return fireBaseConfigurator
    }
}