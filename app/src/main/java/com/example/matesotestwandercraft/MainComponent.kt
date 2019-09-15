package com.example.matesotestwandercraft

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, com.example.matesotestwandercraft.data.DepartementModule::class])
interface MainComponent  {
    fun inject (app: MainActivity) {}
}