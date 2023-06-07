package com.example.testapp.di.viewmodel.repository

import com.example.testapp.di.viewmodel.repository.remotedatasource.RemoteDataSource
import com.example.testapp.di.viewmodel.repository.remotedatasource.RemoteDataSourceImpl

interface Repository {
    fun getRemoteDataSource(remoteDataSource: RemoteDataSourceImpl) : RemoteDataSourceImpl
}