package com.asi.visahackyeah.ui.prefs

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.asi.visahackyeah.R

class PreferencesFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_general, rootKey)
    }

    companion object {

        const val CLASS_TAG = "PrefFragment"

        fun getInstance(): PreferencesFragment {
            val bundle = Bundle()
            val paymentFragment = PreferencesFragment()
            paymentFragment.arguments = bundle
            return paymentFragment
        }
    }
}