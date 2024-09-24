package com.example.recorderapp.dipainhouse.recicle.dipahouse

class DipaHouseProvider {
    fun getAssigned():List<DipaHouseCard>{
        return  listOf<DipaHouseCard>(
            DipaHouseCard(
                "ic_calendar",
                "Dipa Design system",
                "Due.November 06, 2023 . 10:30 Pm",
                "High Probets"
            ),
            DipaHouseCard(
                "ic_calendar",
                "Fleet Shipment",
                "Due.December 10, 2023 . 18:30 Pm",
                "Medium Probets"
            ),
            DipaHouseCard(
                "ic_calendar",
                "Dipa system",
                "Due.November 06, 2023 . 10:30 Pm",
                "Low Probets"
            ),
            DipaHouseCard(
                "ic_calendar",
                "Dipa Design system",
                "Due.November 06, 2023 . 10:30 Pm",
                "High Probets"
            ),
            DipaHouseCard(
                "ic_calendar",
                "Fleet Shipment",
                "Due.December 10, 2023 . 18:30 Pm",
                "Medium Probets"
            ),
            DipaHouseCard(
                "ic_calendar",
                "Dipa system",
                "Due.November 06, 2023 . 10:30 Pm",
                "Low Probets"
            )
        )
    }
    fun getEvents():List<DipaHouseEvent>{
        return  listOf<DipaHouseEvent>(
            DipaHouseEvent(
                "ic_calendar",
                "Fleet Meeting",
                "Due.November 06, 2023 . 10:30 Pm",
                "Join Meeting"
            ) ,
            DipaHouseEvent(
                "ic_dipa_fleet",
                "Dipa House",
                "Due.November 06, 2023 . 10:30 Pm",
                "View Event"
            ),
            DipaHouseEvent(
                "ic_dipa_hiring",
                "Dipa HRING",
                "Due.November 06, 2023 . 10:30 Pm",
                "View Event"
            )
        )
    }
}