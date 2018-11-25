package com.asi.visahackyeah.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.asi.visahackyeah.R
import com.asi.visahackyeah.ui.payment.PaymentFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lunchPaymentFragment()
    }

    fun lunchPaymentFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentLayout, PaymentFragment.getInstance(), PaymentFragment.CLASS_TAG)
            .commit()
        Timber.i("Lunched PaymentFragment")
    }
}