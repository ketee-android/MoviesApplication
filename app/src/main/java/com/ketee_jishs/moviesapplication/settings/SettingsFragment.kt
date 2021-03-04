package com.ketee_jishs.moviesapplication.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.activities.AboutActivity
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.popular_day_movies.PopularDayMoviesFragment
import com.ketee_jishs.moviesapplication.popular_week_movies.PopularWeekMoviesFragment
import com.ketee_jishs.moviesapplication.top_rated_movies.TopRatedMoviesFragment
import com.ketee_jishs.moviesapplication.utils.*
import kotlinx.android.synthetic.main.fragment_settings.*

@Suppress("DEPRECATION")
class SettingsFragment : Fragment() {
    private val sharedDayPopularPrefs by lazy {activity?.getSharedPreferences(PREFS_DAY_POPULAR_NAME, Context.MODE_PRIVATE)}
    private val sharedWeekPopularPrefs by lazy {activity?.getSharedPreferences(PREFS_WEEK_POPULAR_NAME, Context.MODE_PRIVATE)}
    private val sharedTopRatedPrefs by lazy {activity?.getSharedPreferences(PREFS_TOP_RATED_NAME, Context.MODE_PRIVATE)}
    private val sharedAdultPrefs by lazy {activity?.getSharedPreferences(PREFS_ADULT_NAME, Context.MODE_PRIVATE)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutApplicationTextClickable.setOnClickListener {
            startActivity(Intent(context, AboutActivity::class.java))
        }

        switchButtonDayPopular()
        initSettings()
    }

    companion object {
        lateinit var checkAdult: String
    }


    @SuppressLint("InflateParams")
    private fun switchButtonDayPopular() {
        switchButtonPopularDay.setOnCheckedChangeListener { _, isChecked ->
             when (isChecked) {
                true -> setPopularDayVisibility(true, POPULAR_DAY_INVISIBLE)
                false -> setPopularDayVisibility(false, POPULAR_DAY_VISIBLE)
            }
        }

        switchButtonPopularWeek.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> setPopularWeekVisibility(true, POPULAR_WEEK_INVISIBLE)
                false -> setPopularWeekVisibility(false, POPULAR_WEEK_VISIBLE)
            }
        }

        switchButtonTopRated.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> setTopRatedVisibility(true, TOP_RATED_INVISIBLE)
                false -> setTopRatedVisibility(false, TOP_RATED_VISIBLE)
            }
        }

        switchButtonAdult.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    if (checkAdult.toBoolean()) {
                        setAdultVisibility(true, ADULT_INVISIBLE)
                    }
                }
                false -> {
                    if (checkAdult.toBoolean()) {
                        setAdultVisibility(false, ADULT_VISIBLE)
                    }
                }
            }
        }
    }

    private fun setPopularDayVisibility(visibility: Boolean, prefsMode: Int) {
        PopularDayMoviesFragment.popularDayIsVisible = visibility
        savePopularDayVisibility(prefsMode)
    }

    private fun setPopularWeekVisibility(visibility: Boolean, prefsMode: Int) {
        PopularWeekMoviesFragment.popularWeekIsVisible = visibility
        savePopularWeekVisibility(prefsMode)
    }

    private fun setTopRatedVisibility(visibility: Boolean, prefsMode: Int) {
        TopRatedMoviesFragment.topRatedIsVisible = visibility
        saveTopRatedVisibility(prefsMode)
    }

    private fun setAdultVisibility(visibility: Boolean, prefsMode: Int) {
        ItemMovie.isVisible = visibility
        saveAdultVisibility(prefsMode)
    }

    private fun savePopularDayVisibility(prefsMode: Int) = sharedDayPopularPrefs?.edit()?.putInt(KEY_DAY_POPULAR, prefsMode)?.apply()
    private fun savePopularWeekVisibility(prefsMode: Int) = sharedWeekPopularPrefs?.edit()?.putInt(KEY_WEEK_POPULAR, prefsMode)?.apply()
    private fun saveTopRatedVisibility(prefsMode: Int) = sharedTopRatedPrefs?.edit()?.putInt(KEY_TOP_RATED, prefsMode)?.apply()
    private fun saveAdultVisibility(prefsMode: Int) = sharedAdultPrefs?.edit()?.putInt(KEY_ADULT, prefsMode)?.apply()


    private fun initSettings() {
        when (getSavedDayPopular()) {
            POPULAR_DAY_VISIBLE -> switchButtonPopularDay.isChecked = false
            POPULAR_DAY_INVISIBLE -> switchButtonPopularDay.isChecked = true
        }

        when (getSavedWeekPopular()) {
            POPULAR_WEEK_VISIBLE -> switchButtonPopularWeek.isChecked = false
            POPULAR_WEEK_INVISIBLE -> switchButtonPopularWeek.isChecked = true
        }

        when (getSavedTopRated()) {
            TOP_RATED_VISIBLE -> switchButtonTopRated.isChecked = false
            TOP_RATED_INVISIBLE -> switchButtonTopRated.isChecked = true
        }

        when (getSavedAdult()) {
            ADULT_VISIBLE -> switchButtonAdult.isChecked = false
            ADULT_INVISIBLE -> switchButtonAdult.isChecked = true
        }
    }


    private fun getSavedDayPopular() = sharedDayPopularPrefs?.getInt(KEY_DAY_POPULAR, POPULAR_DAY_VISIBLE)
    private fun getSavedWeekPopular() = sharedWeekPopularPrefs?.getInt(KEY_WEEK_POPULAR, POPULAR_WEEK_VISIBLE)
    private fun getSavedTopRated() = sharedTopRatedPrefs?.getInt(KEY_TOP_RATED, TOP_RATED_VISIBLE)
    private fun getSavedAdult() = sharedAdultPrefs?.getInt(KEY_ADULT, ADULT_VISIBLE)

}