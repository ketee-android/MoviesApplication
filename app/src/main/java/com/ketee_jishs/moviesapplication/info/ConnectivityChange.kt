package com.ketee_jishs.moviesapplication.info

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ConnectivityChange : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Connectivity change", Toast.LENGTH_SHORT).show()
    }
}