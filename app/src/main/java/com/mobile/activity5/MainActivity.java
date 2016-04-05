package com.mobile.activity5;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView friendsList = new ListView(this);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillListView fill = new fillListView();
                fill.execute("amigos.txt");
            }
        });

        registerCallback();

    }

    private void populateListView(String[] names) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.friends, names);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

    }

    private void registerCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String message = "You pressed: " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class fillListView extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            AssetManager assetManager = getAssets();
            InputStream input;
            String text = "";

            try {
                input = assetManager.open(params[0]);

                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();

                text = new String(buffer);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject myJson = new JSONObject(s);
                JSONArray myArray = myJson.getJSONArray("Amigos");
                int arrayLength = myArray.length();
                String[] names = new String[arrayLength];
                for(int i=0; i<myArray.length(); i++){
                    names[i] = myArray.getJSONObject(i).getString("name");
                }

                populateListView(names);

            } catch (JSONException  e){
                e.printStackTrace();
            }

        }
    }



}
