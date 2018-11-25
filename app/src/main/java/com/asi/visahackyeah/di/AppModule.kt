package com.asi.visahackyeah.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.asi.visahackyeah.App
import com.asi.visahackyeah.arch.ViewModelFactory
import com.asi.visahackyeah.utils.RxSchedulersFacade
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * App-wide dependencies injections
 */
@Module(subcomponents = [ViewModelSubComponent::class])
class AppModule {

    @Provides
    fun provideContext(app: App): Context{
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
        viewModelBuilder: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelBuilder.build())
    }

    @Provides
    fun provideRxSchedulersFacade(): RxSchedulersFacade {
        return RxSchedulersFacade()
    }
}