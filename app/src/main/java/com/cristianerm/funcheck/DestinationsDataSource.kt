package com.cristianerm.funcheck

class DestinationsDataSource{

    companion object{

        fun createDataSet(): ArrayList<DestinationsInformation>{
            val list = ArrayList<DestinationsInformation>()
            list.add(
                DestinationsInformation(
                    "Paris, France",
                    "New York, USA",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "Rome, Italy",
                    "London, UK",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )

            list.add(
                DestinationsInformation(
                    "Tokyo, Japan",
                    "Lisbon, Portugal",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "Barcelona, Spain",
                    "Honolulu, Hawaii",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "Istanbul, Turkey",
                    "Bangkok, Thailand",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "Agra, India",
                    "Cairo, Egypt",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            return list
        }
    }
}