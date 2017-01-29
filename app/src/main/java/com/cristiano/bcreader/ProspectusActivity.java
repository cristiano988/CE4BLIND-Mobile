package com.cristiano.bcreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Vector;

import projeto.adapters.MedicineArrayAdapter;
import projeto.models.Medicine;

public class ProspectusActivity extends AppCompatActivity {

    private ListView listViewProspectus;
    private Medicine medicine = null;
    private Vector<String> items = null;
    private MedicineArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospectus);

        Intent intent = getIntent();
        medicine = (Medicine)intent.getSerializableExtra("prospectus");
        items = new Vector<String>();
        for(String item : medicine.getFi().getTitles())
            items.add(item);

        listViewProspectus = (ListView)findViewById(R.id.listViewProspectus);
        adapter = new MedicineArrayAdapter(getApplicationContext(),R.id.textView,items);
        listViewProspectus.setAdapter(adapter);
        listViewProspectus.setOnItemClickListener(prospectusListener);
    }

    AdapterView.OnItemClickListener prospectusListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent;
            intent = new Intent(getApplicationContext(),DetailsActivity.class);
            intent.putExtra("section",medicine.getFi().getSections()[i]);
            startActivity(intent);
        }
    };
}
