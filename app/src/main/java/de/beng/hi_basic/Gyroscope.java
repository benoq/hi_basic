package de.beng.hi_basic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope {


    public interface Listener_Rotation {
        void onRotation(float rx, float ry, float rz);
    }

    public void setListener_3D(Listener_Rotation listner_rotation) {
        this.listener_rotation = listener_rotation;
    }

    private Listener_Rotation listener_rotation;

    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;

    Gyroscope(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEventevent) {
                if (listener_rotation != null) {
                    listener_rotation.onRotation(sensorEventevent.values[0], sensorEventevent.values[1], sensorEventevent.values[2]);
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