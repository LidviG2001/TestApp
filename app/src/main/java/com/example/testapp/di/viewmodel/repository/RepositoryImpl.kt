package com.example.testapp.di.viewmodel.repository

import com.example.testapp.di.viewmodel.repository.remotedatasource.RemoteDataSourceImpl

class RepositoryImpl(val remoteDataSource: RemoteDataSourceImpl) : Repository {
    override fun getRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSourceImpl {
        return remoteDataSource
    }

}