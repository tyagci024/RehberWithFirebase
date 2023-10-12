package com.example.rehber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rehber.ViewModel.ContactViewModel
import com.example.rehber.databinding.FragmentListBinding
import com.google.firebase.firestore.FirebaseFirestore


class ListFragment : Fragment() {
    private lateinit var adapterContact : ContactAdapter
    private lateinit var binding: FragmentListBinding
    private val viewModel: ContactViewModel by viewModels()

    private val firestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchContactList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.listFragmentNesnesi = this

        adapterContact = ContactAdapter(viewModel)
        binding.adapterNesnesi = adapterContact



        viewModel.contactList.observe(viewLifecycleOwner, { contactList ->
            // Firestore'dan gelen verileri kullanın (contactList)
            adapterContact.updateData(contactList)
        })

        return binding.root
    }

    fun newPersonAdd() {
        val action = ListFragmentDirections.actionListFragmentToAddFragment()
        findNavController().navigate(action)
    }
}