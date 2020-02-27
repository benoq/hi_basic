package de.beng.hi_basic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.net.sip.SipSession;

public class Accelerometer {

    public interface Listener_3D {
        void onTranslation(float x, float y, float z);
    }

    public void setListner_3D(Listener_3D listener_3D) {
        this.listener_3D = listener_3D;
    }

    private Listener_3D listener_3D;

    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;

    Accelerometer(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (listener_3D != null) {
                    listener_3D.onTranslation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void registerListener () {
        //REMIND:
        sensorManager.registerListener(sensorEventListener, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener () {
        sensorManager.unregisterListener(sensorEventListener);
    }

}
