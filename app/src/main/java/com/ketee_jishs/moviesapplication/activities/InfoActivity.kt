package com.ketee_jishs.moviesapplication.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ketee_jishs.moviesapplication.R
import kotlinx.android.synthetic.main.activity_info.*

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        commentFAB.setOnClickListener {
            addComment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(intent, 0)
        return super.onOptionsItemSelected(item)
    }

    private fun addComment() {
        startActivityForResult(Intent(applicationContext, CommentActivity::class.java), 0)
    }
}