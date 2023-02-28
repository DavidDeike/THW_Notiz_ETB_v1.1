package com.example.thwnotizetb.data.ui.notepad.deletenote

import DeleteNoteViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thwnotizetb.R
import com.example.thwnotizetb.data.model.notepad.Note
import com.google.android.material.snackbar.Snackbar


class DeleteNoteFragment : Fragment() {

    private lateinit var viewModel: DeleteNoteViewModel

    private val args by navArgs<DeleteNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delete_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeleteNoteViewModel::class.java)
        viewModel.observableCurrentNote.observe(viewLifecycleOwner, Observer { currentNote ->
            currentNote?.let { initCurrentNote(currentNote) }
        })
        viewModel.observableDeleteStatus.observe(viewLifecycleOwner, Observer { deleteStatus ->
            deleteStatus?.let { render(deleteStatus) }
        })

        viewModel.initNote(args.noteId)

        cancelDeleteButton.setOnClickListener {
            findNavController(it).popBackStack()
        }

        confirmDeleteButton.setOnClickListener {
            viewModel.deleteNote(args.noteId)
        }
    }

    private fun initCurrentNote(note: Note) {
        noteIdView.text = String.format(getString(R.string.note_detail_id), note.id)
        noteText.text = String.format(getString(R.string.note_detail_text), note.text)
    }

    private fun render(deleteStatus: Boolean) {
        when (deleteStatus) {
            true -> {
                view?.let {
                    findNavController(it).popBackStack(R.id.notesFragment, false)
                }
            }
            false -> Snackbar.make(
                confirmDeleteButton,
                R.string.error_deleting_note,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
