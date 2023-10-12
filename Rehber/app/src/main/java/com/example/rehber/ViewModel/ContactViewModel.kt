package com.example.rehber.ViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rehber.Contact
import com.example.rehber.ContactAdapter
import com.google.firebase.firestore.FirebaseFirestore

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val firestore = FirebaseFirestore.getInstance()
    val contactList: MutableLiveData<List<Contact>> = MutableLiveData()
    val contactMap = mutableMapOf<String, Contact>()




    fun deleteContact(contactId: String) {
        firestore.collection("contacts")
            .document(contactId) // contactId, silinecek belgenin benzersiz kimliğini temsil eder
            .delete()
            .addOnSuccessListener {
                println("Belge başarıyla silindi.")
            }
            .addOnFailureListener { e ->
                println("Belge silinirken hata oluştu: $e")
            }
    }

    fun fetchContactList() {
        firestore.collection("contacts")
            .get()
            .addOnSuccessListener { documents ->
                val contacts = mutableListOf<Contact>() // Yerel değişken ismini değiştirdik
                for (document in documents) {
                    val contactId = document.id
                    val name = document.getString("name")
                    val number = document.getString("number")
                    val contact = Contact(contactId, name!!, number!!)
                    contacts.add(contact)
                    contactMap[contactId] = contact

                }
                contactList.value = contacts // Sınıf düzeyindeki contactList'e atama yapılır
            }
            .addOnFailureListener { exception ->
                // Hata durumunda burası çalışır
                println("hata")
            }
    }


    fun addData(person: Contact){
        firestore.collection("contacts")
            .add(person)
            .addOnSuccessListener { documentReference ->

                println("Firestore'a eklendi")



            }
            .addOnFailureListener { e ->
                println("Firestore'a eklenirken hata oluştu: $e")
            }
    }


}