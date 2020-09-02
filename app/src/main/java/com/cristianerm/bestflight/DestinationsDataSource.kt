package com.cristianerm.bestflight

class DestinationsDataSource{

    companion object{

        fun createDataSet(): ArrayList<DestinationsInformation>{
            val list = ArrayList<DestinationsInformation>()
            list.add(
                DestinationsInformation(
                    "FLO X RIO",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "CUR X SP",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )

            list.add(
                DestinationsInformation(
                    "RG X RIO",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "PR X USA",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "BLU X AM",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsInformation(
                    "FLO X BA",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            return list
        }
    }
}