package com.example.kotlin2lvl_2less.ui.fragments.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin2lvl_2less.App
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.databinding.FragmentNoteDetailBinding
import com.example.kotlin2lvl_2less.models.NoteModel

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
    }

    private fun sendData() = with(binding) {

        cvOne.setOnClickListener {

        }

        btnReady.setOnClickListener {
            val title = detailTitle.text.toString()
            val description = detailDescription.text.toString()
            App().getInstance()?.noteDao()?.insert(NoteModel(title, description))
            findNavController().navigate(R.id.action_noteDetailFragment_to_noteAppFragment)
        }
    }
}