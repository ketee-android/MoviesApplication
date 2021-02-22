package com.ketee_jishs.moviesapplication.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.info.ConnectivityChange

@Suppress("DEPRECATION")
class AboutActivity : AppCompatActivity() {
    private val connectivityChange = ConnectivityChange()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(intent, 0)
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityChange, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectivityChange)
    }
}