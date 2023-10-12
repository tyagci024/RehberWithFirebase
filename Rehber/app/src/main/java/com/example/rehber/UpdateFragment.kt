package com.example.rehber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.rehber.ViewModel.ContactViewModel
import com.example.rehber.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding

    private val viewModel: ContactViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update,container,false)
        binding!!.updateFragmentObject=this

        binding.editTextName.setText(args.kisi.name)
        binding.editTextNumber.setText(args.kisi.number)
        return binding.root
    }

    /*fun update(name:String , number:String){
        val person = Contact(args.kisi.id, name, number)
        val action =UpdateFragmentDirections.actionUpdateFragmentToListFragment()
        findNavController().navigate(action)

    }*/






}