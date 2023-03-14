package com.example.thwnotizetb.data.ui.notepad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thwnotizetb.R

    class NoteListFragment : Fragment() {

        private lateinit var viewModel: NoteViewModel

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.note_list_fragment, container, false)
        }

    }












