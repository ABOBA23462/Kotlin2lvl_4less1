package com.example.kotlin2lvl_2less.ui.fragments.home.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin2lvl_2less.App
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.databinding.FragmentNoteDetailBinding
import com.example.kotlin2lvl_2less.models.NoteModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData() = with(binding) {

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
        val date = current.format(formatter)
        binding.date.text = date.toString()

        btnReady.setOnClickListener {
            if (detailTitle.text.isEmpty() || detailDescription.text.isEmpty()) {
                val title = detailTitle.text.toString()
                val description = detailDescription.text.toString()
                val data = binding.date.text.toString()
                App().getInstance()?.noteDao()?.insert(NoteModel(title, description, data))
                findNavController().navigate(R.id.action_noteDetailFragment_to_noteAppFragment)
            }
        }
    }
}