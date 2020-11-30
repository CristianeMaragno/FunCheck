package com.cristianerm.funcheck

class DestinationsDataSource{

    companion object{

        fun createDataSet(): ArrayList<DestinationsInformation>{
            val list = ArrayList<DestinationsInformation>()
            list.add(
                DestinationsInformation(
                    "FLO X RIO",
                    "14/07/2020 -",
                    "20/07/2020",
                    "All"
                )
            )
            list.add(
                DestinationsInformation(
                    "CUR X SP",
                    "14/07/2020 -",
                    "20/07/2020",
                    "Latam"
                )
            )

            list.add(
                DestinationsInformation(
                    "RG X RIO",
                    "14/07/2020 -",
                    "20/07/2020",
                    "Gol"
                )
            )
            list.add(
                DestinationsInformation(
                    "PR X USA",
                    "14/07/2020 -",
                    "20/07/2020",
                    "All"
                )
            )
            list.add(
                DestinationsInformation(
                    "BLU X AM",
                    "14/07/2020 -",
                    "20/07/2020",
                    "All"
                )
            )
            list.add(
                DestinationsInformation(
                    "FLO X BA",
                    "14/07/2020 -",
                    "20/07/2020",
                    "Azul"
                )
            )
            return list
        }
    }
}