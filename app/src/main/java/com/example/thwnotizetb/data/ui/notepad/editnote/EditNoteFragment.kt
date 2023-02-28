package com.example.thwnotizetb.data.ui.notepad.editnote






import EditNoteViewModel
import com.example.thwnotizetb.data.model.notepad.Note
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thwnotizetb.R


class EditNoteFragment : Fragment() {

    private lateinit var viewModel: EditNoteViewModel

    private val args by navArgs<EditNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditNoteViewModel::class.java)
        viewModel.observableCurrentNote.observe(viewLifecycleOwner, Observer { currentNote ->
            currentNote?.let { initCurrentNote(currentNote) }
        })
        viewModel.observableEditStatus.observe(viewLifecycleOwner, Observer { editStatus ->
            editStatus?.let { render(editStatus) }
        })

        viewModel.initNote(args.noteId)

        setupEditNoteSubmitHandling()
    }

    private fun initCurrentNote(note: Note) {
        editNoteText.setText(note.text)
    }

    private fun render(editStatus: Boolean) {
        when (editStatus) {
            true -> {
                findNavController().popBackStack()
            }
            false -> editNoteText.error = getString(R.string.error_validating_note)
        }
    }

    private fun setupEditNoteSubmitHandling() {
        editNoteText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.editNote(args.noteId, v.text.toString())
                v.closeSoftKeyboard()
                true
            } else {
                false
            }
        }
    }
}
