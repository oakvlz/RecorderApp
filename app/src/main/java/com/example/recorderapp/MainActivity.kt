package com.example.recorderapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.recorderapp.appgraficas.AppGraficasActivity
import com.example.recorderapp.databinding.ActivityMainBinding
import com.example.recorderapp.dipainhouse.DipainHouseActivity
import com.google.zxing.ResultPoint
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.camera.CameraSettings


class MainActivity : AppCompatActivity() {
    private var isPreview=false
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAppRecorder.setOnClickListener {
            startActivity(Intent(this@MainActivity, RecorderActivity::class.java))
        }
        binding.btnGraficas.setOnClickListener {
            startActivity(Intent(this@MainActivity, AppGraficasActivity::class.java))
        }
        binding.btnDiPainHouse.setOnClickListener {
            startActivity(Intent(this@MainActivity, DipainHouseActivity::class.java))
        }
        binding.btnQr.setOnClickListener {
            //startScan()
            qrCodePreview(true)
            binding.tvResult.text = ""
            binding.qrScannerView.decodeSingle(object : BarcodeCallback {
                override fun barcodeResult(result: BarcodeResult) {
                    Log.d("qr", "barcode result: $result")
                    binding.tvResult.text = result.text
                    qrCodePreview(false)
                    //binding.qrScannerView.barcodeView.
                    // startScan()
                    // do your thing with result
                }
                override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
            })
        }
        binding.tvResult.setOnClickListener {
            openUrl()
        }

        binding.qrScannerView.isVisible = isPreview
        val s = CameraSettings()
        s.requestedCameraId = 0 // front/back/etc
        s.isAutoFocusEnabled = true

        binding.qrScannerView.barcodeView.cameraSettings = s


        /*binding.qrScannerView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                Log.d("qr","barcode result: $result")
                binding.tvResult.text=result.text
            }
            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
        })*/
    }
    private fun qrCodePreview(estatus:Boolean){
        isPreview=estatus
        binding.qrScannerView.isVisible=isPreview
        binding.qrScannerView.apply {
            if (isPreview)
                resume()
            else
                pause()
        }
    }

    private fun startScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)//leer todos los codigos
        integrator.setPrompt("Escanea el código QR")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()

    }
    private fun openUrl() {
        val url = binding.tvResult.text.toString()

        if (url.isEmpty()) {
            Toast.makeText(this, "Por favor, escanee primero un QR válido", Toast.LENGTH_SHORT).show()
        } else {
            var fullUrl = url
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                fullUrl = "http://$url"
            }
            if (Patterns.WEB_URL.matcher(fullUrl).matches()) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                startActivity(browserIntent)
            } else {
                Toast.makeText(this, "URL no válida", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null && result.contents != null) {
            binding.tvResult.text = result.contents
        }
    }



}