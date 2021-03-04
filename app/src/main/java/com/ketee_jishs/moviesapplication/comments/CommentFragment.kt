package com.ketee_jishs.moviesapplication.comments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.activities.InfoActivity
import com.ketee_jishs.moviesapplication.info.InfoFragment
import kotlinx.android.synthetic.main.fragment_comment.*

class CommentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        okButton.setOnClickListener {
            commentReady()
            startActivity(Intent(context, InfoActivity::class.java))
        }
    }

    private fun commentReady() {
        when (commentTextField.text.toString()) {
            "" -> InfoFragment.commentForMovieVisibility = true
            else -> {
                InfoFragment.commentForMovieText =
                    StringBuffer().append("Заметка: ").append(commentTextField.text.toString()).toString()
                InfoFragment.commentForMovieVisibility = false
            }
        }
    }
}