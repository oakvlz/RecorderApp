package com.example.recorderapp.dipainhouse.recicle.dipahouse

class DipaHouseProvider {
    companion object{
        val CardListAssigned = listOf<DipaHouseCardsAssigned>(
            DipaHouseCardsAssigned(
                "ic_calendar",
                "Dipa Design system",
                "Due.November 06, 2023 . 10:30 Pm",
                "High Probets"
            ),
            DipaHouseCardsAssigned(
                "ic_calendar",
                "Fleet Shipment",
                "Due.December 10, 2023 . 18:30 Pm",
                "Medium Probets"
            ),
            DipaHouseCardsAssigned(
                "ic_calendar",
                "Dipa system",
                "Due.November 06, 2023 . 10:30 Pm",
                "Low Probets"
            )
        )
        val CardListEvent = listOf<DipaHouseCardsEvent>(
            DipaHouseCardsEvent(
                "ic_calendar",
                "Fleet Meeting",
                "Due.November 06, 2023 . 10:30 Pm",
                "Join Meeting"
            ) ,
            DipaHouseCardsEvent(
                "ic_calendar",
                "Dipa House",
                "Due.November 06, 2023 . 10:30 Pm",
                "View Event"
            ),
            DipaHouseCardsEvent(
                "ic_calendar",
                "Dipa House",
                "Due.November 06, 2023 . 10:30 Pm",
                "View Event"
            )
        )
    }
}