package com.asi.visahackyeah.di

import com.asi.visahackyeah.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * App sub-components binding module
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityMVVMFragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity
}