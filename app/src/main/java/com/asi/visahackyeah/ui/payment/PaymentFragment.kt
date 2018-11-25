package com.asi.visahackyeah.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.asi.visahackyeah.R
import com.asi.visahackyeah.databinding.FragmentPaymentBinding
import com.asi.visahackyeah.di.InjectableFragment
import javax.inject.Inject

class PaymentFragment: Fragment(), InjectableFragment {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewModel: PaymentViewModel? = null
    lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(viewModel == null)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentViewModel::class.java)
    }

    companion object {

        const val CLASS_TAG = "PaymentFragment"

        fun getInstance(): PaymentFragment {
            val bundle = Bundle()
            val paymentFragment = PaymentFragment()
            paymentFragment.arguments = bundle
            return paymentFragment
        }
    }
}