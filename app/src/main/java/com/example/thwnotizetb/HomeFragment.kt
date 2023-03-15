package com.example.thwnotizetb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thwnotizetb.databinding.MenueFragmentBinding

class HomeFragment : Fragment() {

    private var _binding: MenueFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MenueFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notizetbBt.setOnClickListener {
             findNavController().navigate(R.id.notizEtbFragment)
        }
        binding.kalenderBt.setOnClickListener {
            // findNavController().navigate(R.id.action_)
        }
        binding.dASBt.setOnClickListener {
            // findNavController().navigate(R.id.action_)
        }
        binding.tesorBt.setOnClickListener {
            // findNavController().navigate(R.id.action_)
        }
        binding.linksBt.setOnClickListener {
             findNavController().navigate(R.id.linkFragment2)
        }
        binding.serviceBt.setOnClickListener {
            findNavController().navigate(R.id.serviceFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}