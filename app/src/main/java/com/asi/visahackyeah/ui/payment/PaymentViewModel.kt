package com.asi.visahackyeah.ui.payment

import android.app.Application
import com.asi.visahackyeah.arch.BaseViewModel
import com.asi.visahackyeah.utils.RxSchedulersFacade
import javax.inject.Inject

/**
 * ViewModel for [PaymentFragment]
 */
class PaymentViewModel @Inject
constructor(application: Application,
            rxSchedulersFacade: RxSchedulersFacade):
        BaseViewModel(application, rxSchedulersFacade) {

}