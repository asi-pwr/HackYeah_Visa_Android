package com.asi.visahackyeah.common.repository

import android.app.Activity
import android.content.Context
import com.asi.visahackyeah.R
import com.asi.visahackyeah.common.model.PaymentModel
import com.asi.visahackyeah.network.RetrofitFactory
import com.asi.visahackyeah.network.VisaService
import io.reactivex.Observable
import javax.inject.Inject

class VisaRepository @Inject constructor(private val retrofitFactory: RetrofitFactory) {

    private lateinit var apiService: VisaService

    /**
     * Setup backend ip
     */
    fun setUpApi(context: Context){
        val prefs = context.getSharedPreferences(
            context.packageName + "_preferences", Activity.MODE_PRIVATE)
        val ipStr = prefs.getString(context.getString(R.string.pref_backend_ip), "127.0.0.1")
        apiService = retrofitFactory.createService(ipStr, VisaService::class.java)
    }

    fun getPayment(id: String): Observable<PaymentModel>{
        return apiService.getPayment((Integer.parseInt(id) -1).toString())
    }

    fun postPayments(): Observable<Unit>{
        return apiService.postPayments()
    }

}