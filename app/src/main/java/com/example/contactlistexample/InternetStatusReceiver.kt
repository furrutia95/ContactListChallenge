package com.example.contactlistexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

class InternetStatusReceiver:BroadcastReceiver() {

    override fun onReceive(context:Context, intent:Intent) {

        val noConnectivity = intent?.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false) ?: false
        val statusMessage = if (noConnectivity) {
            "No hay Internet :("
        } else {
            "Hay Internet, vo dale mi rey :)"
        }

        Toast.makeText(context, statusMessage, Toast.LENGTH_LONG).show()
    }
}