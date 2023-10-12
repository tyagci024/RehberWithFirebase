package com.example.rehber

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class Contact (    var contactId: String,
                         val name : String , val number: String) : Serializable