package com.asi.visahackyeah.di

import dagger.Subcomponent

/**
 * ViewModelFactory Dagger setup interface - App SubComponent
 */
@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    //fun gpsInfoViewModel(): GpsInfoViewModel
}