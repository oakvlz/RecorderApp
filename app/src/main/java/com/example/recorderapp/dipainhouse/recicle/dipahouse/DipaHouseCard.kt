package com.example.recorderapp.dipainhouse.recicle.dipahouse

data class DipaHouseCard (val  icono : String, val datos:String, val date:String, val probets:String, var selected:Boolean=false  )
data class DipaHouseEvent(val  icono : String, val datos:String, val date:String, val accion:String, var selected:Boolean=false)