package com.example.matesotestwandercraft.data.models

abstract class Commune {
    var code : String = ""
    var nom : String = ""
    var codesPostaux : List<String> = emptyList()
    var codeDepartement : List<String> = emptyList()
    var codeRegion : String = ""
    var departement : Departement? = null
    var region : Region? = null
    var population : Int = 0
    var surface : Float = 0.0f
}