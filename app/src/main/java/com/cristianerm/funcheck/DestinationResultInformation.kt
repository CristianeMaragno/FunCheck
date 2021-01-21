package com.cristianerm.funcheck

data class DestinationResultInformation(

    var attraction_name: String,
    var origin: String = "",
    var destination: String = "",
    var dateGo: String = "",
    var dateBack: String = ""

) {

    override fun toString(): String {
        return "Result(attraction_name='$attraction_name', origin='$origin', destination='$destination', dateGo='$dateGo', dateBack='$dateBack')"
    }


}