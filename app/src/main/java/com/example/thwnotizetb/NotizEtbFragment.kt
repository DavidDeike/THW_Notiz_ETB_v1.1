package com.example.thwnotizetb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thwnotizetb.databinding.NotizetbFragmentBinding

class NotizEtbFragment : Fragment() {

    private var _binding: NotizetbFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = NotizetbFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notizBt.setOnClickListener {
            // findNavController().navigate(R.id.action_)
        }
        binding.etbBt.setOnClickListener {
            // findNavController().navigate(R.id.action_)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}