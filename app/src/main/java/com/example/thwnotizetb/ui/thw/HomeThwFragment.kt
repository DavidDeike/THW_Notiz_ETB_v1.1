package com.example.thwnotizetb.ui.thw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thwnotizetb.databinding.ActivityMainBinding

class HomeThwFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ActivityMainBinding.inflate(inflater)
        return binding.root
    }

   /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val characterAdapter = NoteAdapter()
        binding.noteRecycler.adapter = NoteAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.characterRecycler)

        viewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.submitList(it)
        }
    }*/
}

