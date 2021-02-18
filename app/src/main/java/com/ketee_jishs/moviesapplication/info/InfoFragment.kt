package com.ketee_jishs.moviesapplication.info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding

    companion object {
        lateinit var idFilm: String
    }

    @RequiresApi(Build.VERSION_CODES.N)
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
        viewModel.loadInfoForFilm(idFilm)
        return binding.root
    }
}

