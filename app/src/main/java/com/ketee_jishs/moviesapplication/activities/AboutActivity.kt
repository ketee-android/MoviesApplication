package com.ketee_jishs.moviesapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ketee_jishs.moviesapplication.R

@Suppress("DEPRECATION")
class AboutActivity : AppCompatActivity() {

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
}