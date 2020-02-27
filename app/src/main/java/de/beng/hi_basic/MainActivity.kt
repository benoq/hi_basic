package de.beng.hi_basic

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    lateinit var accelerometer: Accelerometer //lateinit here because: "System services not available to Activities before onCreate()"
    lateinit var gyroscope: Gyroscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accelerometer = Accelerometer(this)
        gyroscope = Gyroscope(this)

        class AccListener_Impl : Accelerometer.Listener_3D {
            override fun onTranslation(x: Float, y: Float, z: Float) {
                if (x > 1.0f) {
                    println("hey" + x)
                    window.decorView.setBackgroundColor(Color.RED)
                }
                if (x < -1.0f) {
                    println("ööööööööööööööööööööööööööö" + x)
                    window.decorView.setBackgroundColor(Color.GREEN)
                }
            }
        }

        class GyroListener_Impl : Gyroscope.Listener_Rotation {
            override fun onRotation(rx: Float, ry: Float, rz: Float) {
                if (rz > 1.0f){
                    window.decorView.setBackgroundColor(Color.BLUE)
                }
                if (rz < -1.0f){
                    window.decorView.setBackgroundColor(Color.YELLOW)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer.registerListener()
        gyroscope.registerListener()
    }

    override fun onPause() {
        super.onPause()

        accelerometer.unregisterListener()
        gyroscope.unregisterListener()
    }
    
}
