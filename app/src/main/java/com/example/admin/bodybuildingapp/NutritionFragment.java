package com.example.admin.bodybuildingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class NutritionFragment extends Fragment {


    ArrayList<String> arrayList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        listView = view.findViewById(R.id.listview);


        fetchData process = new fetchData();
        process.execute();




        return view;

    }
    public class fetchData extends AsyncTask<Void,Void,Void> {
        String data ="";
        String singleParsed ="";


        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://wger.de/api/v2/ingredient/?format=json");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line ="";
                while(line!= null){
                    line = bufferedReader.readLine();
                    data = data + line;

                }
                JSONObject object = new JSONObject(data);
                JSONArray ja = object.getJSONArray("results");


                for (int i =0 ; i<ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    singleParsed = (String) jo.get("name");
                    arrayList.add(singleParsed);


                }

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });




        }


    }
    
}
