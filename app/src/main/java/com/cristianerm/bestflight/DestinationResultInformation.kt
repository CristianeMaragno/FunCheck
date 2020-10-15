package com.cristianerm.bestflight

data class DestinationResultInformation(

    var hour_departure_and_arrival_go: String,

    var hour_departure_and_arrival_back: String,

    var price: String

) {

    override fun toString(): String {
        return "BlogPost(hour_departure_and_arrival_go='$hour_departure_and_arrival_go', hour_departure_and_arrival_back='$hour_departure_and_arrival_back', price='$price')"
    }


}