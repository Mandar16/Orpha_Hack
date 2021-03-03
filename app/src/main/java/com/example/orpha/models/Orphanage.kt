package com.example.orpha.models

data class Orphanage(
    val name:String ="",
    val address:String = "",
    val issues: ArrayList<String>? =null,
    val no_of_children:Int,
    val founders:ArrayList<String>? =null,
    val funding_deficit:Int,
    val list_of_childrens:ArrayList<Children>? = null,
    val list_of_donors:ArrayList<Users>? = null

    )