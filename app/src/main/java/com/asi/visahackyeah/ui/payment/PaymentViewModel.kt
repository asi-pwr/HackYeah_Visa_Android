package com.asi.visahackyeah.ui.payment

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.asi.visahackyeah.arch.BaseViewModel
import com.asi.visahackyeah.common.model.PaymentModel
import com.asi.visahackyeah.common.repository.VisaRepository
import com.asi.visahackyeah.utils.RxSchedulersFacade
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for [PaymentFragment]
 */
class PaymentViewModel @Inject
constructor(application: Application,
            private val schedulers: RxSchedulersFacade,
            private val visaRepository: VisaRepository):
        BaseViewModel(application, schedulers) {

    val paymentModel =  MutableLiveData<PaymentModel>()

    fun getData(id: String, context: Context){
        visaRepository.setUpApi(context)
        disposable.add(visaRepository.getPayment(id)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.computation())
            .subscribe(
                { result -> paymentModel.postValue(result) },
                { error -> Timber.e(error) }
            )
        )
    }

    fun postConfirmationNip(){
        disposable.add(visaRepository.postPayments()
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.computation())
            .subscribe(
                { _ -> Timber.i("Posted at payments") },
                { error -> Timber.e(error) }
            )
        )
    }
}