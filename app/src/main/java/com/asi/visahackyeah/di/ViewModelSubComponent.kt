package com.asi.visahackyeah.di

import com.asi.visahackyeah.ui.payment.PaymentViewModel
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

    fun paymentViewModel(): PaymentViewModel
}