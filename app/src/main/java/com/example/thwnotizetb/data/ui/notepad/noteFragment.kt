package com.example.thwnotizetb.data.ui.notepad

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thwnotizetb.R
import com.example.thwnotizetb.adapter.ClickListener
import com.example.thwnotizetb.adapter.NoteAdapter
import com.example.thwnotizetb.closeSoftKeyboard
import com.example.thwnotizetb.data.model.notepad.Note
import com.example.thwnotizetb.databinding.AddNoteFragmentBinding
import com.google.android.material.snackbar.Snackbar

data class noteFragment: Parcelable {

    constructor(parcel: Parcel) : this

        fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<noteFragment> {
        override fun createFromParcel(parcel: Parcel): noteFragment {
            return noteFragment(parcel)
        }

        override fun newArray(size: Int): Array<noteFragment?> {
            return arrayOfNulls(size)
        }
    }

  class AddNoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel

    private lateinit var binding: AddNoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_note_fragment, container, false)

    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = binding.root
        viewModel = ViewModelProvider.of(this).get(NoteViewModel::class.java)
        viewModel.observableStatus.observe(this) { status ->
            status?.let { render(status) }
        }



        binding.addNoteText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.addNote(v.text.toString())
                v.closeSoftKeyboard()
                true
            } else {
                false
            }
        }
    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    Navigation.findNavController(it).popBackStack()
                }
            }
            false -> binding.addNoteText.error = getString(R.string.error_validating_note)
        }
    }
}
    class DeleteNoteFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel

    private val args by navArgs<NoteFragmentArgs>()

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(R.layout.delete_note_fragment, container, false)
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModel.of(this).get(NoteViewModel::class.java)

    viewModel.observableCurrentNote.observe(viewLifecycleOwner) { currentNote ->
        currentNote?.let { initCurrentNote(currentNote) }
    }
    viewModel.observableDeleteStatus.observe(viewLifecycleOwner) { deleteStatus ->
        deleteStatus?.let { render(deleteStatus) }
    }

    viewModel.initNote(args.noteId)

    cancel_bt.setOnClickListener {
        Navigation.findNavController(it).popBackStack()
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
                Navigation.findNavController(it).popBackStack(R.id.notesFragment, false)
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
    class EditNoteFragment : Fragment() {

        private lateinit var viewModel: NoteViewModel

        private val args by navArgs<NoteFragmentArgs>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.edit_note_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
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
    class NoteDetailFragment : Fragment() {

        private lateinit var viewModel: NoteViewModel

        private val args by navArgs<NoteFragmentArgs>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.note_detail_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
            viewModel.observableNote.observe(viewLifecycleOwner, Observer { note ->
                note?.let { render(note) } ?: renderNoteNotFound()
            })

            editNoteButton.setOnClickListener {
                val action = NoteFragmentDirections.actionNoteDetailToEditNote(args.noteId)
                Navigation.findNavController(it).navigate(action)
            }

            deleteNoteButton.setOnClickListener {
                val action = NoteFragmentDirections.actionNoteDetailToDeleteNote(args.noteId)
                Navigation.findNavController(it).navigate(action)
            }
        }

        override fun onResume() {
            super.onResume()
            viewModel.getNote(args.noteId)
        }

        private fun render(note: Note) {
            noteIdView.text = String.format(getString(R.string.note_detail_id), note.id)
            noteText.text = String.format(getString(R.string.note_detail_text), note.text)
        }

        private fun renderNoteNotFound() {
            noteIdView.visibility = View.GONE
            noteText.visibility = View.GONE
            view?.let {
                Snackbar.make(it, R.string.error_loading_note, Snackbar.LENGTH_LONG).show()
            }
        }
    }
    class NoteListFragment : Fragment() {

        private val clickListener: ClickListener = this::onNoteClicked

        private val recyclerViewAdapter = NoteAdapter(clickListener)

        private lateinit var viewModel: NoteViewModel

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.note_list_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            setupRecyclerView()

            viewModel = ViewModelProvider.of(this).get(NoteViewModel::class.java)
            viewModel.observableNoteList.observe(viewLifecycleOwner) { notes ->
                notes?.let { render(notes) }
            }

            fab_bt.setOnClickListener {
                val action = NoteListFragment.actionNotesToAddNot()
                Navigation.findNavController(it).navigate(action)
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
            val action = NoteFragmentDirections.actionNotesToNoteDetail(note.id)
            findNavController().navigate(action)
        }

        private fun setupRecyclerView() {
            notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
            notesRecyclerView.adapter = recyclerViewAdapter
            notesRecyclerView.setHasFixedSize(true)
        }
    }
    class SearchFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.search_fragment, container, false)
        }
    }








}


