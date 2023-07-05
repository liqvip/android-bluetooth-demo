package cn.blogss.bluetooth

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var btScan: Button
    private lateinit var btScan2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)

        initView()
        initData()
    }

    private fun initView() {
        btScan = findViewById(R.id.bt_ble_scan)
        btScan2 = findViewById(R.id.bt_ble_scan2)

        btScan.setOnClickListener(this)
        btScan2.setOnClickListener(this)
    }

    private fun initData() {
        // Request ble permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "permission allowed")
                openBle()
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION), 0)
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.bt_ble_scan -> {
                startActivity(Intent(this, BleScanActivity::class.java))
            }
            R.id.bt_ble_scan2 -> {
                startActivity(Intent(this, BleScanActivity2::class.java))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 0 && grantResults.isNotEmpty()) {
            Log.i(TAG, "grantResults size: ${grantResults.size}")
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "permission allowed")
                openBle()
            } else {
                Log.i(TAG, "permission denied")
            }
        }
    }

    private fun openBle() {

    }


}