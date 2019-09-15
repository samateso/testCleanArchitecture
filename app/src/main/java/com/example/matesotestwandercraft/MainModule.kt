package com.example.matesotestwandercraft

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideApplicationContext(app : MainActivity) : Context {
        return app.applicationContext
    }
}