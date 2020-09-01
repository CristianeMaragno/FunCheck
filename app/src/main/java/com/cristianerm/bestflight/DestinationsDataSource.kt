package com.cristianerm.bestflight

class DestinationsDataSource{

    companion object{

        fun createDataSet(): ArrayList<DestinationsHome>{
            val list = ArrayList<DestinationsHome>()
            list.add(
                DestinationsHome(
                    "FLO X RIO",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsHome(
                    "CUR X SP",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )

            list.add(
                DestinationsHome(
                    "RG X RIO",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsHome(
                    "PR X USA",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsHome(
                    "BLU X AM",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            list.add(
                DestinationsHome(
                    "FLO X BA",
                    "14/07/2020 -",
                    "20/07/2020"
                )
            )
            return list
        }
    }
}