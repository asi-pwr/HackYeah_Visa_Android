package com.asi.visahackyeah.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.ArrayMap
import com.asi.visahackyeah.di.ViewModelSubComponent
import com.asi.visahackyeah.ui.payment.PaymentViewModel

import java.util.concurrent.Callable

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory for ViewModel instances
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory
/**
 * ViewModels injected into creators ArrayMap
 * @param viewModelSubComponent Dagger SubComponent ViewModel interface
 */
@Inject
constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {

    private val creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    init {
        creators[PaymentViewModel::class.java] = Callable { viewModelSubComponent.paymentViewModel() }
    }

    /**
     * creates ViewModels
     * @param modelClass (viewModelChildName).class
     * @param <T> class
     * @return ViewModel custom instance
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("Model class not found$modelClass")
        }

        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
