package com.example.kotlin2lvl_2less.ui.fragments.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin2lvl_2less.App
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.databinding.FragmentNoteAppBinding
import com.example.kotlin2lvl_2less.models.NoteModel
import com.example.kotlin2lvl_2less.ui.adapter.NoteAppAdapter

class NoteAppFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppBinding
    private val noteAppAdapter = NoteAppAdapter(this::onItemClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setList()
    }

    private fun initialize() {
        binding.noteRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAppAdapter
        }
    }

    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteAppFragment_to_noteDetailFragment)
        }

        binding.itemGrid.setOnClickListener {
            App.preferenceHelper.onSaveOnBoardState = false
            binding.noteRecView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.itemGrid.isVisible = false
            binding.itemLinear.isVisible = true
        }

        binding.itemLinear.setOnClickListener {
            App.preferenceHelper.onSaveOnBoardState = false
            binding.noteRecView.layoutManager = LinearLayoutManager(requireContext())
            binding.itemGrid.isVisible = true
            binding.itemLinear.isVisible = false
        }
    }

    private fun setList() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { list ->
            noteAppAdapter.setList(list as ArrayList<NoteModel>)
        }
    }

    private fun onItemClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete?")
        builder.setPositiveButton("Delete") { dialog, _ ->
            App.appDatabase?.noteDao()?.delete(noteModel)
        }
        builder.setNegativeButton("Cancel") { dialog, id ->
            dialog.cancel()
        }
        builder.show()
    }
}