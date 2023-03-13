package com.example.thwnotizetb.data.ui.notepad.addnote

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.thwnotizetb.R
import com.example.thwnotizetb.closeSoftKeyboard
import com.example.thwnotizetb.databinding.AddNoteFragmentBinding


class AddNoteFragment : Fragment() {

    private lateinit var viewModel: AddNoteViewModel

    private lateinit var binding : AddNoteFragmentBinding

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
        viewModel = ViewModelProvider.of(this).get(AddNoteViewModel::class.java)
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
                    findNavController(it).popBackStack()
                }
            }
            false -> binding.addNoteText.error = getString(R.string.error_validating_note)
        }
    }
}