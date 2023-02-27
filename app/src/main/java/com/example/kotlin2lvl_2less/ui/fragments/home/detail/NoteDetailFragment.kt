package com.example.kotlin2lvl_2less.ui.fragments.home.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin2lvl_2less.App
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.databinding.FragmentNoteDetailBinding
import com.example.kotlin2lvl_2less.models.NoteModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var backgroundColor = "#1B1A1A"

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
        setupListener()
    }

    private fun setupListener() = with(binding) {
        back.setOnClickListener {
            findNavController().navigate(R.id.action_noteDetailFragment_to_noteAppFragment)
        }

        cvOne.setOnClickListener {
            backgroundColor = "#1E1E1E"
            firstInd.isVisible = true
            secondInd.isVisible = false
            thirdInd.isVisible = false
        }

        cvTwo.setOnClickListener {
            backgroundColor = "#EBE4C9"
            firstInd.isVisible = false
            secondInd.isVisible = true
            thirdInd.isVisible = false
        }

        cvThird.setOnClickListener {
            backgroundColor = "#571818"
            firstInd.isVisible = false
            secondInd.isVisible = false
            thirdInd.isVisible = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData() = with(binding) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM")
        val date = current.format(formatter)
        binding.date.text = date.toString()

        btnReady.setOnClickListener {
            if (detailTitle.text.isNotEmpty() || detailDescription.text.isNotEmpty()) {
                val title = detailTitle.text.toString()
                val description = detailDescription.text.toString()
                val dateItem = binding.date.text.toString()
                App().getInstance()?.noteDao()?.insert(
                    NoteModel(
                        title,
                        description,
                        itemColor = backgroundColor,
                        itemDate = dateItem
                    )
                )
                findNavController().navigateUp()
            }
        }
    }
}