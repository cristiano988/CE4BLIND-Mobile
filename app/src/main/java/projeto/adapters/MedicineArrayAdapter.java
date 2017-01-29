package projeto.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cristiano.bcreader.R;

import java.util.Vector;

/**
 * Created by crist on 05/01/2017.
 */

public class MedicineArrayAdapter extends ArrayAdapter{

    private Vector<String> fields = null;

    public MedicineArrayAdapter(Context context, int resource, Vector<String> fields) {
        super(context, resource, fields);
        this.fields = fields;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =  inflater.inflate(R.layout.list_row,parent,false);
        TextView textView = (TextView)row.findViewById(R.id.textView);
        textView.setText(fields.get(position));

        return row;
    }

}
