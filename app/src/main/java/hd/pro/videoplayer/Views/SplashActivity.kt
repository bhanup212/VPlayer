package hd.pro.videoplayer.Views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import hd.pro.videoplayer.R

class SplashActivity : AppCompatActivity() {

     val TAG  :String= "SplashActivity"
    private val REQUEST_CODE  = 234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        requestPermission()

    }
    private fun requestPermission(){
        val permissions = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissions != PackageManager.PERMISSION_GRANTED){
            Log.e(TAG,"Permission not granted")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_CODE)
        }else{
            launchMainActivity()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE -> {
                if (!grantResults.isEmpty() ||  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG,"Storage Permission granted")
                    launchMainActivity()
                }else{
                    Log.d(TAG,"Storage permission denied")
                }
            }
        }
    }
    private fun launchMainActivity(){
        Handler().postDelayed({
            //launching main activity here
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

    }

}
