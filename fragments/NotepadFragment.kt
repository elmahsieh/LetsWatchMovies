package com.ehsieh2.letswatchtv.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehsieh2.letswatchtv.NotesAdapter
import com.ehsieh2.letswatchtv.databinding.FragmentNotepadBinding

class NotepadFragment : Fragment() {
    private var _binding: FragmentNotepadBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NotesAdapter
    private val notes = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotepadBinding.inflate(inflater, container, false)
        loadNotes()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.buttonSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun setupRecyclerView() {
        adapter = NotesAdapter(notes)
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.notesRecyclerView.adapter = adapter
    }

    private fun saveNote() {
        val noteText = binding.editTextNote.text.toString()
        if (noteText.isNotEmpty()) {
            notes.add(noteText)
            saveNotesToPreferences()
            adapter.notifyItemInserted(notes.size - 1)
            binding.editTextNote.text.clear()
        }
    }

    private fun loadNotes() {
        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE)
        val savedNotes = sharedPrefs?.getStringSet("SAVED_NOTES", emptySet())
        notes.clear()
        notes.addAll(savedNotes ?: emptySet())
    }

    private fun saveNotesToPreferences() {
        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE)
        with (sharedPrefs?.edit()) {
            this?.putStringSet("SAVED_NOTES", notes.toSet())
            this?.apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
