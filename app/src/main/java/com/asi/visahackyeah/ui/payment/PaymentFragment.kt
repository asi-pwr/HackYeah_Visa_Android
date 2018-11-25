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
import com.asi.visahackyeah.common.model.PaymentModel
import com.asi.visahackyeah.databinding.FragmentPaymentBinding
import com.asi.visahackyeah.di.InjectableFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PaymentFragment: Fragment(), InjectableFragment {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewModel: PaymentViewModel? = null
    private lateinit var binding: FragmentPaymentBinding

    private var paymentId: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)

        paymentId = arguments?.getString("id")

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(viewModel == null)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentViewModel::class.java)

        binding.viewModel = viewModel
        observePaymentModel()

        if(paymentId != null && context != null)
            viewModel?.getData(paymentId!!, context!!)
    }

    fun observePaymentModel(){
        viewModel?.paymentModel?.observe(this, androidx.lifecycle.Observer {
           handlePaymentModel(it)
        })
    }

    fun handlePaymentModel(paymentModel: PaymentModel){
        binding.paymentModel = paymentModel
        val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.ENGLISH)
        binding.formattedDate = formatter.format(Date(paymentModel.date * 1000))
        if(paymentModel.value != null)
            binding.formattedValue = String.format("%.2f PLN", paymentModel.value!! / 100.0f)

        binding.executePendingBindings()

        binding.paymentAcceptButton.setOnClickListener {
            viewModel?.postConfirmationNip()
        }
    }

    companion object {

        const val CLASS_TAG = "PaymentFragment"

        fun getInstance(id: String): PaymentFragment {
            val bundle = Bundle()
            bundle.putString("id", id)
            val paymentFragment = PaymentFragment()
            paymentFragment.arguments = bundle
            return paymentFragment
        }
    }
}