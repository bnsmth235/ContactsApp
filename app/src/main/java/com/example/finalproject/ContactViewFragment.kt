package com.example.finalproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.FragmentContactViewerBinding

class ContactViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val binding = FragmentContactViewerBinding.inflate(inflater, container, false)
        var curContact: Contact? = null
        val bundle = this?.arguments

        if(bundle != null) {
            curContact = bundle.getParcelable("contact-vals")
        }

        if (curContact != null) {
            binding.fullName.setText(curContact.firstName + " " + curContact.lastName)
            binding.emailText.setText(curContact.email)
            binding.phoneNumberText.setText(curContact.phone)
        }

        binding.callButton.setOnClickListener{
            if(!binding.phoneNumberText.text.equals("Phone Number")){
                val call = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + binding.phoneNumberText.text))
                //startActivity(call)
            }
        }

        binding.callButton2.setOnClickListener{
            if(!binding.phoneNumberText.text.equals("Phone Number")){
                val call = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + binding.phoneNumberText.text))
                //startActivity(call)
            }
        }

        binding.messageButton.setOnClickListener{
            if(!binding.phoneNumberText.text.equals("")){
                val sms = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + binding.phoneNumberText.text))
                startActivity(sms)
            }
        }

        binding.messageButton2.setOnClickListener{
            if(!binding.phoneNumberText.text.equals("Phone Number")){
                val sms = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + binding.phoneNumberText.text))
                startActivity(sms)
            }
        }

        binding.emailButton.setOnClickListener {
            if(!binding.emailText.text.equals("Email")){
                val email = Intent(Intent.ACTION_SENDTO).apply {
                    putExtra(Intent.EXTRA_EMAIL, binding.emailText.text)
                }
                //startActivity(email)
            }
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commit()
            //findNavController().navigate(R.id.action_contactViewFragment2_to_homeFragment)
        }


        val callback = requireActivity().onBackPressedDispatcher.addCallback {
            parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commit()
            //findNavController().navigate(R.id.action_contactViewFragment2_to_homeFragment)
        }

        return binding.root
    }
}