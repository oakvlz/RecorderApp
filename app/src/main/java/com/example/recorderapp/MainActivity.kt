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
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recorderapp.dataStorage.FileRecord
import com.example.recorderapp.dataStorage.FileRecorderAdapter
import com.example.recorderapp.dataStorage.GrabProvider
import com.example.recorderapp.databinding.ActivityMainBinding
import com.example.recorderapp.play.PlayAudioPlayer
import com.example.recorderapp.recorder.Recorder
import com.example.recorderapp.recorder.StatusViewModel
import com.example.recorderapp.wave.WaveFormView
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
    private lateinit var listaRecord:List<FileRecord>
    private lateinit var adapterRecord: FileRecorderAdapter
    private var colorSelected= 0
    private var colorBase=0
    private var posicionAnt=-1
    private lateinit var statusVM : StatusViewModel
    private lateinit var wave :WaveFormView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        statusVM = ViewModelProvider(this)[StatusViewModel::class.java]
        statusVM.isStatusGrabar.value = false
        statusVM.isStatusStopGrabar.value = false
        statusVM.isStatusPauseGrabar.value = false
        statusVM.isStatusPlayRepro.value = false
        statusVM.isStatusPauseRepro.value = false
        statusVM.isStatusStopRepro.value = false
        validateDirectory("grabacion")
        colorSelected = ContextCompat.getColor(this@MainActivity, R.color.red)
        colorBase = ContextCompat.getColor(this@MainActivity, R.color.invisible)
        binding.btnPlayRepro.isVisible=false
        binding.btnPlayGrab.isVisible=false
        //binding.wd.isVisible = true
        binding.btnPlayGrab.setOnClickListener { statusVM.isStatusGrabar.value = true }
        binding.btnStopGrab.setOnClickListener {statusVM.isStatusStopGrabar.value = true }
        binding.btnPauseGrab.setOnClickListener {}
        binding.btnPlayRepro.setOnClickListener {statusVM.isStatusPlayRepro.value = true }
        binding.btnPauseRepro.setOnClickListener {}
        binding.btnStopRepro.setOnClickListener {statusVM.isStatusStopRepro.value =true }
        binding.btnPermRead.setOnClickListener {solicitaPermiso(Manifest.permission.READ_EXTERNAL_STORAGE) }
        binding.btnPermWrite.setOnClickListener { solicitaPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE) }
        binding.btnPermRcorder.setOnClickListener { solicitaPermiso(Manifest.permission.RECORD_AUDIO) }
        statusVM.isStatusDirectory.observe(this) { valor ->
            Log.d("Viewmodel","isStatusDirectory => ${valor.toString()}")
            if (valor) {
                initRecyclerView()
            }
        }
        statusVM.isStatusGrabar.observe(this) { valor ->
            Log.d("Viewmodel","isStatusGrabar => ${valor.toString()}")
            if (valor) {
                checkPermiso()
                validarArchivo()
                setViewVisible(binding.btnStopGrab,true)
                setViewVisible(binding.btnPauseGrab,true)
                setViewVisible(binding.btnPlayGrab,false)
                setViewVisible(binding.btnPlayRepro,false)

            }

           // binding.wd.addAmplitude(recorder.maxAmplitude.toString().toFloat())
        }
        statusVM.isStatusStopGrabar.observe(this) { valor ->
            Log.d("Viewmodel","isStatusStopGrabar => ${valor.toString()}")
            if (valor) {
                recorder.stop()
                setViewVisible(binding.btnStopGrab,false)
                setViewVisible(binding.btnPauseGrab,false)
                setViewVisible(binding.btnPlayGrab,true)
                if (audioFile!!.exists()){
                    setViewVisible(binding.btnPlayRepro,true)
                }
            }
        }
        statusVM.isStatusPlayRepro.observe(this) { valor ->
            Log.d("Viewmodel","isStatusPlayRepro => ${valor.toString()}")
            if (valor) {
                audioFile?.let { it -> player.playFile(it)
                    setViewVisible(binding.btnStopRepro,true)
                    setViewVisible(binding.btnPauseRepro,true)
                    setViewVisible(binding.btnPlayRepro,false)
                    setViewVisible(binding.btnPlayGrab,false)

                }
            }
        }
        statusVM.isStatusStopRepro.observe(this) { valor ->
            Log.d("Viewmodel","isStatusStopRepro => ${valor.toString()}")
            if (valor) {
                player.stop()
                setViewVisible(binding.btnStopRepro,false)
                setViewVisible(binding.btnPauseRepro,false)
                setViewVisible(binding.btnPlayRepro,true)
                setViewVisible(binding.btnPlayGrab,true)
            }
        }
        checkPermiso()
    }
    private fun validateDirectory(carpetaDir: String){
        directorio = "${getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}/${carpetaDir}/"
        statusVM.isStatusDirectory.value = false
        val storageDir = File(directorio)
        Log.d("ruta","$storageDir")
        if (!storageDir.exists()) {
            Log.d("ruta0","$storageDir")
            storageDir.mkdir()
            Log.d("ruta2","$storageDir")
        }
        statusVM.isStatusDirectory.value = true
        //obtenerLista()
    }

    private fun validarArchivo() {
       //var archivoNom = "${UUID.randomUUID().toString()}.mp3"
       var archivoNom = "5316d867-7491-4775-98e2-e32c91b7161c.mp3"
       Log.d("File","$directorio$archivoNom")
       audioFile = File("$directorio$archivoNom")
       try {
           recorder.start(audioFile!!)
           //audioFile = archivo
           binding.tvInicioGrab.isVisible = true

       } catch (e:Error){
           Log.d("Error",e.message.toString())
       }

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
        binding.btnPlayGrab.isVisible=true
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
    fun initRecyclerView() {

        listaRecord = GrabProvider().getlista(directorio)!!
        posicionAnt=-1
        binding.rvStorage.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        adapterRecord = FileRecorderAdapter(listaRecord, colorSelected, colorBase, onClickListener = { FileRecord, Posicion -> onItemSelected(FileRecord, Posicion) })
        binding.rvStorage.adapter = adapterRecord
    }
    private fun onItemSelected(item: FileRecord, posicion:Int) {
        Log.d("APP", "Valores => [$posicion| anterior($posicionAnt)] = $item")
        if (posicionAnt < 0)
            posicionAnt = posicion
        if (posicionAnt != posicion && posicionAnt >= 0) {
            listaRecord[posicionAnt].selected = false
            adapterRecord.notifyItemChanged(posicionAnt)
            posicionAnt = posicion
        }
        Log.d("APP","Valores => [$posicion| anterior($posicionAnt)] = $item")
        listaRecord[posicion].selected= !item.selected
        adapterRecord.notifyItemChanged(posicion)
    }
}