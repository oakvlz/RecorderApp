package com.example.recorderapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.recorderapp.databinding.ActivityMainBinding
import com.example.recorderapp.play.PlayAudioPlayer
import com.example.recorderapp.recorder.Recorder
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {
    private val recorder by lazy {
        Recorder(applicationContext)
    }
    private val player by lazy {
        PlayAudioPlayer(applicationContext)
    }
    private var audioFile: File? = null
    private val permisoAceptado = arrayListOf(false,false,false)
    private var permisoNom = ""
    //private lateinit var archivo:File
    private lateinit var directorio:String
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateDirectory("grabacion")
        binding.btnPlayRepro.isVisible=false
        binding.btnPlayGrab.isVisible=true
        binding.btnPlayGrab.setOnClickListener {
           /* File(cacheDir, "audio.mp3").also {
                recorder.start(it)
                audioFile = it
                  }*/
            //Verificar si existe directorio de trabajo**ok
            //Crea directorio de trabajo si no existe**ok

            // crear un recylce view con las grbacipnes
            // crear funcionamiento de bloqueo de botones para evitar mal funcionamiento
            // crear funcion de lectura de archivos
            //crear funcionde reproducciond earchivos
            // data clas que separe los datos del archivoy envie a l recycle
            validarArchivo()
            setViewVisible(binding.btnStopGrab,true)
            setViewVisible(binding.btnPauseGrab,true)
            setViewVisible(binding.btnPlayGrab,false)
            setViewVisible(binding.btnPlayRepro,false)
        }
        binding.btnStopGrab.setOnClickListener {
            recorder.stop()
            setViewVisible(binding.btnStopGrab,false)
            setViewVisible(binding.btnPauseGrab,false)
            setViewVisible(binding.btnPlayGrab,true)
            if (audioFile!!.exists()){
                setViewVisible(binding.btnPlayRepro,true)
            }

        }
        binding.btnPauseGrab.setOnClickListener {
        }
        binding.btnPlayRepro.setOnClickListener {
            audioFile?.let { it -> player.playFile(it)
                setViewVisible(binding.btnStopRepro,true)
                setViewVisible(binding.btnPauseRepro,true)
                setViewVisible(binding.btnPlayRepro,false)
                setViewVisible(binding.btnPlayGrab,false)
                }
        }
        binding.btnPauseGrab.setOnClickListener {
        }
        binding.btnStopRepro.setOnClickListener { player.stop()
            setViewVisible(binding.btnStopRepro,false)
            setViewVisible(binding.btnPauseRepro,false)
            setViewVisible(binding.btnPlayRepro,true)
            setViewVisible(binding.btnPlayGrab,true)

        }
        binding.btnPermRead.setOnClickListener {solicitaPermiso(Manifest.permission.READ_EXTERNAL_STORAGE) }
        binding.btnPermWrite.setOnClickListener { solicitaPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE) }
        binding.btnPermRcorder.setOnClickListener { solicitaPermiso(Manifest.permission.RECORD_AUDIO) }
        checkPermiso()
    }
    private fun validateDirectory(carpetaDir: String){
        directorio = "${getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}/${carpetaDir}/"
        val storageDir = File(directorio)
        if (!storageDir.exists()) {
            storageDir.mkdir()
        }
    }

    private fun validarArchivo() {
       var archivoNom = "${UUID.randomUUID().toString()}.mp3"
       Log.d("File","$directorio$archivoNom")
       audioFile = File("$directorio$archivoNom")
       try {
           recorder.start(audioFile!!)
           //audioFile = archivo
           binding.tvInicioGrab.isVisible = true

       } catch (e:Error){
           Log.d("Error",e.message.toString())
       }
       /*if (file.exists()) {
           // Existe archivo
           if (file.canExecute()) {
               // Lógica si el archivo es ejecutable, si es necesario
           }
       } else {
           try {
               // Crear el archivo y empezar la grabación
               if (file.createNewFile()) {
                   recorder.start(file)
                   audioFile = file
                   binding.tvInicioGrab.isVisible = true
               } else {
                   Log.e("validarArchivo", "No se pudo crear el archivo.")
               }
           } catch (e: IOException) {
               Log.e("crear archivo", "Error al crear archivo", e)
           }
       }*/
   }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermiso(){
        permisoAceptado[0]= checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        permisoAceptado[1]= checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        permisoAceptado[2]= checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        //permisoAceptado[1]= checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED

        asignaValorText(binding.tvPermRcorder,binding.btnPermRcorder,0)
        asignaValorText(binding.tvPermWrite,binding.btnPermWrite,1)
        asignaValorText(binding.tvPermRead,binding.btnPermRead,2)
    }
    private fun asignaValorText(vista:TextView, boton:TextView, posicion:Int){
        vista.text = if(permisoAceptado[posicion])  "APROBADO" else "NEGADO"
        boton.isVisible = !permisoAceptado[posicion]
    }
    private fun setViewVisible(vista: View, valor:Boolean){ vista.isVisible = valor }
    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun solicitaPermiso(tipo:String){ // The registered ActivityResultCallback gets the result of this request
        //permisoNom = tipo
        //requestPermissionLauncher.launch(permisoNom)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            when {
                shouldShowRequestPermissionRationale(tipo) -> {
                    Log.d("checkSelfPermission", "SNAK BAR")
                    Snackbar.make(findViewById(R.id.btnPermWrite),"Notificación bloqueada, revise la configuración de la App.", Snackbar.LENGTH_LONG)
                        .setAction("Configuración") {
                        // Responds to click on the action
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }.show()
                }
                else -> {
                    Log.d("checkSelfPermission", "else")
                    // The registered ActivityResultCallback gets the result of this request
                    permisoNom=tipo
                    requestPermissionLauncher.launch(permisoNom)
                }
            }
        } else {
            when(tipo){
                Manifest.permission.RECORD_AUDIO  -> requestPermissions(arrayOf<String>(tipo)!!, 200)
                Manifest.permission.READ_EXTERNAL_STORAGE  -> requestPermissions(arrayOf<String>(tipo)!!, 200)
                Manifest.permission.WRITE_EXTERNAL_STORAGE  -> requestPermissions(arrayOf<String>(tipo)!!, 200)
            }
        }
    }
    override fun onRequestPermissionsResult( requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("onRequestPermissionsResult","$permissions $grantResults")
        if(requestCode == 200 && permissions.isNotEmpty() && grantResults.isNotEmpty()){
            permissions.forEachIndexed { index, item ->
                if ( grantResults[index] == 0 ){
                    when(item){
                        Manifest.permission.RECORD_AUDIO -> asignaValorText(binding.tvPermRcorder, binding.btnPermRcorder, 0)
                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> asignaValorText(binding.tvPermWrite, binding.btnPermWrite, 1)
                        Manifest.permission.READ_EXTERNAL_STORAGE -> asignaValorText(binding.tvPermRead, binding.btnPermRead, 2)
                    }
                }
            }
        }
    }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your app.
            Log.d("requestPermissionLauncher =>","$permisoNom => Permiso aceptado")
            when(permisoNom){
                Manifest.permission.RECORD_AUDIO -> { setViewVisible(binding.btnPlayGrab, true) }
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> { setViewVisible(binding.btnPlayGrab, true) }
                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    binding.btnPlayGrab.isInvisible=true
                }
            }
        } else {
            // Explain to the user that the feature is unavailable because the features requires a permission that the user has denied. At the
            // same time, respect the user's decision. Don't link to system settings in an effort to convince the user to change their decision.
            Log.d("requestPermissionLauncherGps =>","$permisoNom => Permiso rechazado")
            when(permisoNom){
                Manifest.permission.RECORD_AUDIO -> {
                    binding.btnPlayGrab.isInvisible=false
                }
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                    binding.btnPlayGrab.isInvisible=false
                }
                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    binding.btnPlayGrab.isInvisible=false
                }

            }
        }
    }
}