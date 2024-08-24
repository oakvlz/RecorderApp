package com.example.recorderapp.play

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
   // fun reset()
}