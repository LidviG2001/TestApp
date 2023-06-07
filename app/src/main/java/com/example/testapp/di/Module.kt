package com.example.testapp.di

import com.example.testapp.di.viewmodel.repository.RepositoryImpl
import com.example.testapp.di.viewmodel.repository.remotedatasource.RemoteDataSourceImpl
import com.example.testapp.di.viewmodel.repository.remotedatasource.firebase.FireBaseConfigurator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Module() {

    @Provides
    @Singleton
    fun provideRemoteDataSource(firebaseConfigurator : FireBaseConfigurator) : RemoteDataSourceImpl{
        return RemoteDataSourceImpl(firebaseConfigurator)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource : RemoteDataSourceImpl) : RepositoryImpl{
        return RepositoryImpl(remoteDataSource)
    }

}