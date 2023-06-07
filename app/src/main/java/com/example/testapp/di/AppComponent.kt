package com.example.testapp.di

import com.example.testapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}