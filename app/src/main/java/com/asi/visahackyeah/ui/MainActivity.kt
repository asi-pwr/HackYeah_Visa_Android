package com.asi.visahackyeah.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.asi.visahackyeah.R
import com.asi.visahackyeah.ui.payment.PaymentFragment
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.R.attr.data
import android.app.Notification.DEFAULT_LIGHTS
import android.app.Notification.DEFAULT_VIBRATE
import android.app.PendingIntent
import android.content.Context
import android.content.Intent



class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private var notificationId = 1;

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()

        lunchPaymentFragment()
    }

    fun lunchPaymentFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentLayout, PaymentFragment.getInstance(), PaymentFragment.CLASS_TAG)
            .commit()
        Timber.i("Lunched PaymentFragment")
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

            val mBuilder = NotificationCompat.Builder(this,"default")
                .setDefaults(DEFAULT_VIBRATE or DEFAULT_LIGHTS)
                .setSmallIcon(R.drawable.menu_about)
                .setContentTitle(title)
                .setContentIntent(contentIntent)
                .setContentText("Próba autoryzacji płatności")
                .setAutoCancel(true)

            val mNotificationManager = getSystemService(Co ntext.NOTIFICATION_SERVICE) as NotificationManager

            notificationId++
            mNotificationManager.notify(notificationId, mBuilder.build())

            Timber.i("Received push notification: %s", data.toString())
        }

        pusher.connect()
    }
}