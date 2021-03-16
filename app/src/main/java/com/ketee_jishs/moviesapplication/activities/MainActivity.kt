package com.ketee_jishs.moviesapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ketee_jishs.moviesapplication.databinding.ActivityMainBinding
import com.ketee_jishs.moviesapplication.utils.KEY_THEME
import com.ketee_jishs.moviesapplication.utils.PREFS_NAME
import com.ketee_jishs.moviesapplication.utils.THEME_DAY
import com.ketee_jishs.moviesapplication.utils.THEME_NIGHT
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPrefs by lazy {getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)
        onClickAbout()
        changeTheme()
        initTheme()
    }

    private fun changeTheme() {
        binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_NIGHT)
                    super.recreate()
                }
                false -> {
                    setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_DAY)
                    super.recreate()
                }
            }
        }
    }

    private fun setTheme (themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    private fun saveTheme(theme: Int) = sharedPrefs.edit().putInt(KEY_THEME, theme).apply()

    private fun initTheme() {
        when (getSavedTheme()) {
            THEME_DAY -> binding.switchButton.isChecked = false
            THEME_NIGHT -> binding.switchButton.isChecked = true
        }
    }

    private fun getSavedTheme() = sharedPrefs.getInt(KEY_THEME, THEME_DAY)

    private fun onClickAbout() {
        binding.imageButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}