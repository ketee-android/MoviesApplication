package com.ketee_jishs.moviesapplication.info

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.databinding.FragmentInfoBinding


@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class InfoFragment : Fragment() {
    companion object {
        lateinit var idFilm: String
    }

    lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        val viewModel: InfoViewModel by lazy {
            ViewModelProviders.of(this).get(InfoViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.executePendingBindings()

        val loadResultReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                viewModel.loadResult(
                    intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA),
                    intent.getStringExtra(DETAILS_TITLE_EXTRA),
                    intent.getStringExtra(DETAILS_ORIGINAL_TITLE_EXTRA),
                    intent.getStringExtra(DETAILS_VOTE_AVERAGE_EXTRA),
                    intent.getStringExtra(DETAILS_RUNTIME_EXTRA),
                    intent.getStringExtra(DETAILS_DESCRIPTION_EXTRA),
                    intent.getStringExtra(DETAILS_OVERVIEW_EXTRA)
                )
            }
        }

        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
        getFilm()

        return binding.root
    }

    private fun getFilm() {
        context?.let {
            it.startService(Intent(it, DetailsService::class.java).apply {
                putExtra(ID_EXTRA, idFilm)
            })
        }
    }
}

