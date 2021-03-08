package com.ketee_jishs.moviesapplication.info

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.drawee.backends.pipeline.Fresco
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.databinding.FragmentInfoBinding

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class InfoFragment : Fragment() {
    companion object {
        lateinit var idFilm: String
    }

    lateinit var binding: FragmentInfoBinding
    val viewModel: InfoViewModel by lazy {
        ViewModelProviders.of(this).get(InfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Fresco.initialize(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getFilmInfoFromRemoteSource(idFilm)

        return binding.root
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                getInfoForFilm(appState.movieData[0])
            }
            is AppState.Error -> {
                Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getInfoForFilm(infoList: InfoList) {
        saveMovie(infoList)
        val uri: Uri = Uri.parse("https://image.tmdb.org/t/p/w500${infoList.posterPath}")
        viewModel.setInfoForFilm(
            infoList.id,
            infoList.name,
            infoList.originalTitle,
            infoList.rating,
            infoList.time,
            infoList.description,
            infoList.overview,
            uri,
            true
        )
    }

    private fun saveMovie(infoList: InfoList) {
        viewModel.saveMovieToDB(
            InfoList(
                infoList.id,
                infoList.name,
                infoList.originalTitle,
                infoList.rating,
                infoList.time,
                infoList.description,
                infoList.overview,
                infoList.posterPath
            )
        )
    }
}

