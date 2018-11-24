package com.asi.visahackyeah.arch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.asi.visahackyeah.utils.RxSchedulersFacade

import io.reactivex.disposables.CompositeDisposable

/**
 * Abstract base ViewModel
 */
abstract class BaseViewModel(application: Application,
                             protected val rxSchedulersFacade: RxSchedulersFacade):
        AndroidViewModel(application) {

    protected var disposable = CompositeDisposable()

    val loadingStatus = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
