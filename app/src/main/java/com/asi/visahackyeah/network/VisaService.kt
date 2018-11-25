package com.asi.visahackyeah.network

import com.asi.visahackyeah.common.model.PaymentModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VisaService {

    @GET("payments/{id}")
    fun getPayment(@Path("id") id: String): Observable<PaymentModel>

    @POST("payments")
    fun postPayments(): Observable<Unit>
}