package com.example.sensres_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView txtAcelerometro;
    private TextView txtLuz;
    private TextView txtGravedad;
    private TextView txtProximidad;

    private SensorManager sensorManager;
    DecimalFormat formato1 = new DecimalFormat("#.##");

    private Sensor sensorAcelerometro;
    private SensorEventListener oyenteAcelerometro = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            txtAcelerometro.setText(formato1.format(x)+" m/s, " + formato1.format(y) +" m/s, " + formato1.format(z) +" m/s " );
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private Sensor sensorLuz;
    private SensorEventListener oyenteLuz = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float niveLuz = event.values[0];
            txtLuz.setText(niveLuz+" lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private Sensor sensorGravedad;
    private SensorEventListener oyenteGravedad = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            txtGravedad.setText(formato1.format(x)+" m/s, " + formato1.format(y) +" m/s, " + formato1.format(z) +" m/s " );
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private Sensor sensorProximidad;
    private SensorEventListener oyenteProximidad = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float distancia = event.values[0];
            txtProximidad.setText(distancia+" cm");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configSensores();
        configViews();
    }

    private void configSensores() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorGravedad = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    private void configViews() {
        txtAcelerometro = findViewById(R.id.txtAcelerometro);
        txtLuz = findViewById(R.id.txtLuz);;
        txtGravedad = findViewById(R.id.txtGravedad);;
        txtProximidad = findViewById(R.id.txtproximidad);;
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(oyenteAcelerometro, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(oyenteGravedad, sensorGravedad, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(oyenteLuz, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(oyenteProximidad, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(oyenteProximidad);
        sensorManager.unregisterListener(oyenteLuz);
        sensorManager.unregisterListener(oyenteGravedad);
        sensorManager.unregisterListener(oyenteProximidad);
    }
}
