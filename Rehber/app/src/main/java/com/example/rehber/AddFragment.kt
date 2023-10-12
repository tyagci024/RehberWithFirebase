package com.example.rehber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.rehber.ViewModel.ContactViewModel
import com.example.rehber.databinding.FragmentAddBinding
import com.example.rehber.databinding.FragmentListBinding
import com.google.firebase.firestore.FirebaseFirestore


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding?=null
    private val binding get()= _binding!!

    private val viewModel: ContactViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        _binding!!.addFragment=this

        return binding.root
    }


    fun personSave (name : String, number : String){

            val person = Contact("0", name,
                number)
            viewModel.addData(person)
        findNavController().navigate(R.id.action_addFragment_to_listFragment)


    }



    }


