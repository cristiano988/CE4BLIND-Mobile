package com.cristiano.bcreader;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Field;
import java.util.Vector;

import projeto.adapters.MedicineArrayAdapter;
import projeto.models.Medicine;

public class MainActivity extends ActionBarActivity {

    public static final int ADD_DATA = 001;
    public static final int REMOVE_DATA = 002;
    public static final int SCAN = 003;
    public static final int MEDICINE_RECEIVED = 004;
    private final String ERROR_MSG = "Error!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infarmed_activity_main);

        /*((Button) findViewById(R.id.button_cargar)).setOnClickListener(btn_listener);
        ((Button)findViewById(R.id.button_descargar)).setOnClickListener(btn_listener);
        ((Button)findViewById(R.id.button_list)).setOnClickListener(btn_listener);*/

        startActivityForResult(new Intent(this,BaseScan.class), SCAN);
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

    View.OnClickListener btn_listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.button_cargar:
                    startActivity(new Intent(getApplicationContext(), AddBarCode.class));
                    break;
                case R.id.button_descargar:
                    startActivity(new Intent(getApplicationContext(), RemoveBarCode.class));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case SCAN:
                if(resultCode == RESULT_OK){
                    if(data == null)
                        break;
                    String _code = data.getStringExtra("result");
                    Log.i(this.getPackageName(), _code);
                }
                if(resultCode == MEDICINE_RECEIVED)
                {

                }
                break;

            default:
                Log.i(this.getPackageName(), ERROR_MSG);
        }
    }
}
