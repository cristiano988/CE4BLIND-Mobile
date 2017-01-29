package com.cristiano.bcreader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Vector;

import projeto.adapters.MedicineArrayAdapter;
import projeto.models.Medicine;

public class MedicineActivity extends AppCompatActivity {

    private ListView listViw;
    private Medicine medicine = null;
    private Vector<String> files = null;
    private MedicineArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infarmed_activity_main);

        Intent intent = getIntent();
        medicine = (Medicine)intent.getSerializableExtra("medicine");
        if(medicine != null){
            listViw =(ListView)findViewById(R.id.listView);
            firstPopulateAdapter();
        }

    }

    private void firstPopulateAdapter(){
        files = new Vector<>();
        if(medicine.getFi() != null)
            files.add("Folheto Informativo");
        if(medicine.getRcm() != null)
            files.add("RCM");
        adapter = new MedicineArrayAdapter(this,R.id.textView,files);
        listViw.setAdapter(adapter);
        listViw.setOnItemClickListener(medicineListener);
    }

    AdapterView.OnItemClickListener medicineListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent;
            switch (i){
                case 0:
                    intent = new Intent(getApplicationContext(),ProspectusActivity.class);
                    intent.putExtra("prospectus",medicine);
                    startActivity(intent);
                    break;
                default:
                    break;
            }

        }
    };

}
