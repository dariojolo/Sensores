package dariojolo.com.sensores;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    RelativeLayout rl;
    TextView tv;
    SensorManager sm;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = (RelativeLayout)findViewById(R.id.layout);
        tv = (TextView)findViewById(R.id.texto);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Toast.makeText(MainActivity.this,"Entro en sensor changed",Toast.LENGTH_SHORT).show();
        String texto = String.valueOf(event.values[0]);
        tv.setText(texto);

        float valor = Float.parseFloat(texto);

        if (valor == 3){
            rl.setBackgroundColor(Color.BLUE);
            Toast.makeText(MainActivity.this,"valor == 0",Toast.LENGTH_SHORT).show();
        }else {
            rl.setBackgroundColor(Color.RED);
            Toast.makeText(MainActivity.this,"valor != 0",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Toast.makeText(MainActivity.this,"Entro en Accuracy changed",Toast.LENGTH_SHORT).show();
        String texto = String.valueOf(accuracy);
        tv.setText(texto);

        float valor = Float.parseFloat(texto);

        if (valor == 0){
            rl.setBackgroundColor(Color.BLUE);
            Toast.makeText(MainActivity.this,"valor == 0",Toast.LENGTH_SHORT).show();
        }else {
            rl.setBackgroundColor(Color.RED);
            Toast.makeText(MainActivity.this,"valor != 0",Toast.LENGTH_SHORT).show();
        }
    }
}
