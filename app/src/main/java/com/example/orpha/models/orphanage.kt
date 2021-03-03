package com.example.orpha.models

data class orphanage(
    val name:String ="",
    val address:String = "",
    val issues: ArrayList<String>,
    val no_of_students:Int,
    val founders:ArrayList<String>,
    val fundind_deficit:Int,
    val list_of_doners:ArrayList<Users>

)

