package com.cristianerm.bestflight

data class DestinationsHome(

    var destination: String,

    var date_go: String,

    var date_back: String

) {

    override fun toString(): String {
        return "BlogPost(destination='$destination', date_go='$date_go', date_back='$date_back')"
    }


}