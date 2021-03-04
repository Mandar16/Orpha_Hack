package com.example.orpha.models

data class Children(
    val name:String,
    val age:Int,
    val childImageUrl: String
    ){
    //** Empty [Constructor] for firebase*/

    constructor() : this("",0,"")

    constructor(name: String,age: Int) :
            this(name,age,"")



}
