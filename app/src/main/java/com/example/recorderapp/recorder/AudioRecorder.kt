package com.example.recorderapp.recorder

import java.io.File

interface InitAudioRecorder {
    fun start(outputFile: File)
    fun stop()
}