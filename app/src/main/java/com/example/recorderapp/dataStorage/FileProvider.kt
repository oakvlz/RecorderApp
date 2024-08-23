package com.example.recorderapp.dataStorage

import java.io.File

class GrabProvider{
    //private lateinit var  lista : List<FileRecord>

  /* companion object{



       val FileListGrab = listOf<FileRecord>(
           FileRecord("$filesStorage")
           /*FileRecord("archivo1","red"),
           FileRecord("archivo2","blue"),
           FileRecord("archivo3", "morado"),
           FileRecord("archivo4","verde"),
           FileRecord("archivo5","cafe"),
           FileRecord("archivo6","blue")*/

       )
   }*/
    fun getlista(directorio : String):List<FileRecord>?{
    //val filesStorage = File(directorio).listFiles()
      //lista =filesStorage.map {it -> FileRecord(it.name,false,"" ) }
      return File(directorio).listFiles()?.map {archivo -> FileRecord(archivo.name,false,"" ) }
    }
}
