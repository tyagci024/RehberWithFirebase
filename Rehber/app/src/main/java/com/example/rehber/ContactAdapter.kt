package com.example.rehber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rehber.ViewModel.ContactViewModel
import com.example.rehber.databinding.ItemLayoutBinding

class ContactAdapter(private val viewModel: ContactViewModel):RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    var contactList=emptyList<Contact>()

    class ContactViewHolder (val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ContactViewHolder {
        val binding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return ContactViewHolder(binding)    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        val currentContact = contactList[position]
        holder.binding.contact=currentContact

        holder.binding.delete.setOnClickListener {
            viewModel.deleteContact(currentContact.contactId)

            contactList = contactList.filter { it != currentContact } // Silinen öğeyi kaldır
            notifyDataSetChanged()




        }

        holder.itemView.setOnClickListener {
            val action =ListFragmentDirections.actionListFragmentToUpdateFragment(currentContact)
            holder.itemView.findNavController().navigate(action)


        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
    fun updateData(list: List<Contact>) {
        contactList = list
        notifyDataSetChanged()
    }
}