package com.example.recorderapp.dipainhouse.recicle.dipahouse

import android.media.Image

data class DipaHouseCardsAssigned ( val  icono : String,val datos:String,val date:String, val probets:String,var selected:Boolean=false  )
data class DipaHouseCardsEvent(val  icono : String,val datos:String,val date:String,  val accion:String)