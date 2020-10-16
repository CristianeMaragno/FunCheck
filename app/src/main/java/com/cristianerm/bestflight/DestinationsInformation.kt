package com.cristianerm.bestflight

data class DestinationsInformation(

    var destination: String,

    var date_go: String,

    var date_back: String,

    var airline: String

) {

    override fun toString(): String {
        return "BlogPost(destination='$destination', date_go='$date_go', date_back='$date_back', airline='$airline')"
    }


}