package com.example.orpha.models

data class orphanage(
    val name:String ="",
    val address:String = "",
    val issues: ArrayList<String>,
    val no_of_children:Int,
    val founders:ArrayList<String>,
    val funding_deficit:Int,
    val list_of_donors:ArrayList<Users>,
    val list_of_childrens:ArrayList<Children>
    )
