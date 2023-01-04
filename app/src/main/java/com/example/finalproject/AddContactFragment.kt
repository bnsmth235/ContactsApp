package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddContactBinding.inflate(inflater, container, false)
        val viewModel = AddContactViewModel()
        var isError = false

        viewModel.fnError.observe(viewLifecycleOwner){
            if(viewModel.fnError.value == true) {
                binding.firstNameEdit.setBackgroundResource(R.drawable.errorborder)
                isError = true
            }else{
                binding.firstNameEdit.setBackgroundResource(android.R.drawable.edit_text)
            }
        } //first name error box

        viewModel.lnError.observe(viewLifecycleOwner){
            if(viewModel.lnError.value == true) {
                binding.lastNameEdit.setBackgroundResource(R.drawable.errorborder)
                isError = true
            }else{
                binding.lastNameEdit.setBackgroundResource(android.R.drawable.edit_text)
            }
        } //last name error box

        viewModel.phoneError.observe(viewLifecycleOwner){
            if(viewModel.phoneError.value == true) {
                binding.phoneNumberEditText.setBackgroundResource(R.drawable.errorborder)
                isError = true
            }else{
                binding.phoneNumberEditText.setBackgroundResource(android.R.drawable.edit_text)
            }
        } //phone number error box

        viewModel.emailError.observe(viewLifecycleOwner){
            if(viewModel.emailError.value == true) {
                binding.emailEditText.setBackgroundResource(R.drawable.errorborder)
                isError = true
            }else{
                binding.emailEditText.setBackgroundResource(android.R.drawable.edit_text)
            }
        }//email error box

        binding.saveButton.setOnClickListener{
            val firstNameText = binding.firstNameEdit.text.toString()
            val lastNameText = binding.lastNameEdit.text.toString()
            val phoneNumberText = binding.phoneNumberEditText.text.toString()
            val emailText = binding.emailEditText.text.toString()

            viewModel.createContact(firstNameText, lastNameText, phoneNumberText, emailText)

            if(!isError) {
                findNavController().navigate(R.id.action_addContactFragment2_to_homeFragment)
            }


        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigate(R.id.action_addContactFragment2_to_homeFragment)
        }

        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_addContactFragment2_to_homeFragment)
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_addContactFragment2_to_homeFragment)
        }

        return binding.root
    }

}