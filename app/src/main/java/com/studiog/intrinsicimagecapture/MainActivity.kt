package com.studiog.intrinsicimagecapture

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.studiog.intrinsicimagecapture.ui.MainScreen
import com.studiog.intrinsicimagecapture.ui.ProcessingAutomation
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : ComponentActivity() {

    val REQUEST_CAPTURE_FOCUSED = 1
    val REQUEST_CAPTURE_DIFFUSED = 2
    val REQUEST_RECORD_AUDIO = 3

    val REQUEST_CAMERA = 200
    val REQUEST_STORAGE = 100

    var mCurrentPhotoPath: String = ""
    var imageUri: Uri? = null

    var imageName by mutableStateOf("")
    var showDialog by mutableStateOf(false)
    var showText by mutableStateOf("")
    var loading by mutableStateOf(false)
    var showImage by mutableStateOf(false)
    lateinit var focused: File
    var fDone by mutableStateOf(false)
    lateinit var diffused: File
    var dDone by mutableStateOf(false)
    lateinit var responseBitmap: Bitmap
    var responded by mutableStateOf(false)
    var showPreview by mutableStateOf(true)

    lateinit var fBitmap: Bitmap
    lateinit var dBitmap: Bitmap

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val API: ServerAPI = retrofit.create(ServerAPI::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                if(showImage) ProcessingAutomation(mainActivity = this)
                else MainScreen(mainActivity = this, goToTutorial = {goToTutorial()})
            }
        }
    }

    fun camera(){
        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
        } else captureImage(REQUEST_CAPTURE_FOCUSED)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
                        //captureImage(REQUEST_CAPTURE_FOCUSED)
                        externalStorage()

                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_STORAGE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureImage(REQUEST_CAPTURE_FOCUSED)
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun externalStorage() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_STORAGE)
            } else {
                captureImage(REQUEST_CAPTURE_FOCUSED)
            }
        } else captureImage(REQUEST_CAPTURE_FOCUSED)
    }

    fun captureImage(code: Int) {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        try {
            val imageFile = File.createTempFile("temp_pgoto",".jpeg",dir)
            mCurrentPhotoPath = imageFile.absolutePath
            imageUri = FileProvider.getUriForFile(this,"com.studiog.intrinsicimagecapture.fileprovider",imageFile)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)

            startActivityForResult(intent,code)
        } catch (e: IOException ) {
            e.printStackTrace()
        }
    }

    fun setName(name:String) {
        this.imageName = name
    }



    fun saveImage(bitmap:Bitmap,name:String, isFocused: Boolean) {
        try {
            val dir: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"/Intrinsic")
            dir.mkdir()
            val file: File = File(dir,name)

            if(isFocused) {
                focused = file
                fDone = false
                fBitmap = bitmap
            }
            else  {
                diffused = file
                dDone = false
                dBitmap = bitmap
            }

            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
            galleryAddPic(file)
            if(isFocused) fDone=true
            else dDone=true
            fOut.flush()
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun galleryAddPic(file :File) {
        val mediaScanIntent:Intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        mediaScanIntent.setData(Uri.fromFile(file))
        this.sendBroadcast(mediaScanIntent)
    }

    fun rotate(bitmap: Bitmap,orientation: Int?): Bitmap {
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> return rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> return rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> return rotateBitmap(bitmap, 270)
        }

        return bitmap
    }

    private fun rotateBitmap(bitmap: Bitmap, degrees: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAPTURE_FOCUSED && resultCode == RESULT_OK) {
            var bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)

            var exif: ExifInterface? = null
            try {
                contentResolver.openInputStream(imageUri!!).use { stream ->
                    exif = ExifInterface(stream!!)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            AsyncTask.execute {
                val orientation: Int? = exif?.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
                val name:String = imageName
                saveImage(rotate(bitmap, orientation!!), "F_$name.jpeg",true)
            }


            captureImage(REQUEST_CAPTURE_DIFFUSED)
        }

        if (requestCode == REQUEST_CAPTURE_DIFFUSED && resultCode == RESULT_OK) {
            var bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)

            var exif: ExifInterface? = null
            try {
                contentResolver.openInputStream(imageUri!!).use { stream ->
                    exif = ExifInterface(stream!!)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            AsyncTask.execute {
                val orientation: Int? = exif?.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
                val name:String = imageName
                saveImage(rotate(bitmap, orientation!!), "D_$name.jpeg",false)
            }

            //showDialog("$imageName image pair saved to Pictures>Intrinsic!")
            showImage()
        }

        if(requestCode == REQUEST_RECORD_AUDIO && resultCode == RESULT_OK && data != null) {
            val text: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
            imageName = text[0]
        }
    }

    fun goToImageProcessingActivity() {
//        val intent = Intent(this, ImageProcessingActivity::class.java)
//        startActivity(intent)

        val myIntent: Intent =  Intent(Intent.ACTION_VIEW, Uri.parse("https://portal.quantcyte.org/"));
        startActivity(myIntent);

    }

    fun goToIntrinsicChallangeSubmitions() {
//        val intent = Intent(this, ImageProcessingActivity::class.java)
//        startActivity(intent)

        val myIntent: Intent =  Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSf5hFeKHlg7tphUjhKVnNs_8BIiyG4PkID3GbFIs-pvmU0sgA/viewform"));
        startActivity(myIntent);

    }

    fun goToTutorial() {
        val intent = Intent(this, TutorialActivity::class.java)
        startActivity(intent)
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
    }

    fun recordAudio() {
        var intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en-US")
        startActivityForResult(intent, REQUEST_RECORD_AUDIO)
    }

    fun showDialog(text:String){
        showDialog = true
        showText = text
    }

    fun showImage(){
        showImage=true
    }

    fun processImages() {
        loading=true

        if(Constants.isNetworkAvailable(this)){

            val reqF : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),focused)
            val F: MultipartBody.Part = MultipartBody.Part.createFormData("F",focused.name,reqF)

            val reqD : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),diffused)
            val D: MultipartBody.Part = MultipartBody.Part.createFormData("D",diffused.name,reqD)

            val name: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "I_$imageName.jpeg")


            val loginCall: Call<ResponseBody?>? = API.processImages(F,D,name)

            loginCall?.enqueue(object: Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    loading=false

                    if(response.isSuccessful){
                        val body:ResponseBody? = response.body()
                        if(body==null)  Log.d("ERROR","ERROR")
                        else{
                            responseBitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                            showPreview = false
                            responded = true
                        }
                    } else {
                        Log.d("ERROR","ERROR")
                    }
                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    loading=false
                    Toast.makeText(applicationContext,"There was an error with the server, plase try again!",Toast.LENGTH_SHORT).show()
                }
            })
        } else{
            loading=false
            Toast.makeText(applicationContext,"No internet connection!",Toast.LENGTH_SHORT).show()
        }
    }

    fun dissmissDialog(){
        showDialog = false
        setName("")
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }
}


