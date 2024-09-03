package com.example.recorderapp.apprecorder.recorder

import java.io.File

interface InitAudioRecorder {


    fun start(outputFile: File)
    fun stop()
}