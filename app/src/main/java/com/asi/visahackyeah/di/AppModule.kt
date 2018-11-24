package com.asi.visahackyeah.di

import android.content.Context
import com.asi.visahackyeah.App
import dagger.Module
import dagger.Provides

/**
 * App-wide dependencies injections
 */
@Module(subcomponents = [ViewModelSubComponent::class])
class AppModule {

    @Provides
    fun provideContext(app: App): Context{
        return app.applicationContext
    }
}