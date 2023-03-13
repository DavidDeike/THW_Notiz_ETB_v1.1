package com.example.thwnotizetb.data.ui.notepad.deletenote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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
        viewModel = ViewModel.of(this).get(DeleteNoteViewModel::class.java)

        viewModel.observableCurrentNote.observe(viewLifecycleOwner, { currentNote ->
            currentNote?.let { initCurrentNote(currentNote) }
        })
        viewModel.observableDeleteStatus.observe(viewLifecycleOwner, { deleteStatus ->
            deleteStatus?.let { render(deleteStatus) }
        })

        viewModel.initNote(args.noteId)

        cancel_bt.setOnClickListener {
            findNavController(it).popBackStack()
        }

        cDelete_bt.setOnClickListener {
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
                cDelete_bt,
                R.string.error_deleting_note,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
