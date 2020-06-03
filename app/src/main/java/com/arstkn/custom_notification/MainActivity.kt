package com.arstkn.custom_notification

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.arstkn.custom_notification.App.Companion.CHANNEL_ID

class MainActivity : AppCompatActivity() {
    private var notificationManager: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        notificationManager = NotificationManagerCompat.from(this)
    }

    public fun showNotification(v: View) {
        val collapsedView = RemoteViews(packageName, R.layout.notification_collapsed)
        val expandedView = RemoteViews(packageName, R.layout.notification_expanded)

        val clickIntent = Intent(this, NotificationReceiver::class.java)
        val clickPendingIntent = PendingIntent.getBroadcast(this, 0, clickIntent, 0)

        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World!!")
        expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.ic_launcher_foreground)
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCustomContentView(collapsedView)
            .setCustomBigContentView(expandedView)
            .build()

        notificationManager!!.notify(1, notification)
    }
}
