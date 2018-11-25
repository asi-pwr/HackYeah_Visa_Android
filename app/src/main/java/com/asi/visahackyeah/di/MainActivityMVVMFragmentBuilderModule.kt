package com.asi.visahackyeah.di

import com.asi.visahackyeah.ui.payment.PaymentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * MainActivity fragment injection point
 */
@Module
abstract class MainActivityMVVMFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePaymentFragment(): PaymentFragment
}
