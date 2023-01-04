package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModel = ContactsViewModel()

        binding.addContactButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addContactFragment2)
        }

        binding.homeRecyclerView.adapter = HomeAdapter(viewModel.contacts){
            val viewFragment = ContactViewFragment()
            var bundle = Bundle()
            bundle.putParcelable("contact-vals", it)
            viewFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, viewFragment)
                .commit()
        }

        binding.homeRecyclerView.layoutManager = LinearLayoutManager(view?.context)


        return binding.root
    }



}



