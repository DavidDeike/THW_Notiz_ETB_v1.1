package com.example.thwnotizetb.data.ui.notepad.notelist




import ClickListener
import com.example.thwnotizetb.data.model.notepad.Note
import NoteAdapter
import NoteListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thwnotizetb.R


class NoteListFragment : Fragment() {

    private val clickListener: ClickListener = this::onNoteClicked

    private val recyclerViewAdapter = NoteAdapter(clickListener)

    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        viewModel.observableNoteList.observe(viewLifecycleOwner) { notes ->
            notes?.let { render(notes) }
        }

        fab.setOnClickListener {
            val action = NoteListFragmentDirections.actionNotesToAddNote()
            findNavController(it).navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun render(noteList: List<Note>) {
        recyclerViewAdapter.updateNotes(noteList)
        if (noteList.isEmpty()) {
            notesRecyclerView.visibility = View.GONE
            notesNotFound.visibility = View.VISIBLE
        } else {
            notesRecyclerView.visibility = View.VISIBLE
            notesNotFound.visibility = View.GONE
        }
    }

    private fun onNoteClicked(note: Note) {
        val action = NoteListFragmentDirections.actionNotesToNoteDetail(note.id)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView() {
        notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        notesRecyclerView.adapter = recyclerViewAdapter
        notesRecyclerView.setHasFixedSize(true)
    }
}