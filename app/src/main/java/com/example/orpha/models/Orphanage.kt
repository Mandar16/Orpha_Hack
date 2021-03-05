package com.example.orpha.models

data class Orphanage(
    var name:String ="",
    val address:String = "",
    val phoneNumber :String ="",
    val email:String ="",
    val upiid:String ="",
    val issues: ArrayList<String>? =null,
    val no_of_children:Int =0,
    val founders:ArrayList<String>? =null,
    val funding_deficit:Int =0,
    val list_of_childrens:ArrayList<Children>? = null,
    val list_of_donors:ArrayList<Users>? = null

    )
