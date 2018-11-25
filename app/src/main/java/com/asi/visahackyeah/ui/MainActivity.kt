package com.asi.visahackyeah.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.asi.visahackyeah.R
import com.asi.visahackyeah.ui.payment.PaymentFragment
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject
import android.app.NotificationManager
import android.app.Notification.DEFAULT_LIGHTS
import android.app.Notification.DEFAULT_VIBRATE
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.asi.visahackyeah.ui.prefs.PreferencesFragment


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private var notificationId = 1

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()

        if(intent.extras != null && intent.extras["notifDisp"] == 1) lunchPaymentFragment()
        else lunchPreferencesFragment()
    }

    fun lunchPaymentFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentLayout, PaymentFragment.getInstance(), PaymentFragment.CLASS_TAG)
            .commit()
        Timber.i("Lunched PaymentFragment")
    }

    fun lunchPreferencesFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentLayout, PreferencesFragment.getInstance(), PreferencesFragment.CLASS_TAG)
            .commit()
        Timber.i("Lunched PreferencesFragment")
    }

    fun subscribe(){
        val options = PusherOptions()
        options.setCluster("eu")
        val pusher = Pusher("35db3a17fbbf7b8a74b6", options)

        val channel = pusher.subscribe("my-channel")

        channel.bind("my-event") { _, _, data ->

            val contentIntent: PendingIntent
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("notifDisp", 1)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            contentIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val prefs = getSharedPreferences(
                this.packageName + "_preferences", Activity.MODE_PRIVATE)
            val userId = prefs.getString(getString(R.string.pref_client_id), "-1")

            val apiUserIdStr = (data.subSequence(10, data.indexOf(',', 10, true)))
            val apiUserId = apiUserIdStr.toString().trim(' ')
            if(userId == apiUserId) {
                val builder = NotificationCompat.Builder(this, "default")
                    .setDefaults(DEFAULT_VIBRATE or DEFAULT_LIGHTS)
                    .setSmallIcon(R.drawable.menu_about)
                    .setContentTitle(title)
                    .setContentIntent(contentIntent)
                    .setContentText("Próba autoryzacji płatności")
                    .setAutoCancel(true)

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                notificationId++
                notificationManager.notify(notificationId, builder.build())
            }

            Timber.i("Received push notification: %s", data.toString())
        }

        pusher.connect()
    }
}