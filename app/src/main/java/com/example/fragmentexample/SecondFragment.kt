package com.example.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragmentexample.databinding.ActivityMainBinding
import com.example.fragmentexample.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            if (!binding.etLogin.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "is not null", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "is null", Toast.LENGTH_LONG).show()
            }
        }
    }
}