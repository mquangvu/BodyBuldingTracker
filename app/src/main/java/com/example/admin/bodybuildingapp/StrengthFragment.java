package com.example.admin.bodybuildingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static java.lang.String.valueOf;


public class StrengthFragment extends Fragment {
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> idList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    JSONArray array = new JSONArray();
    JSONObject idObj = new JSONObject();
    JSONObject idObj1 = new JSONObject();
    JSONObject idObj2 = new JSONObject();
    JSONObject idObj3= new JSONObject();
    JSONObject idObj4 = new JSONObject();
    JSONObject idObj5 = new JSONObject();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_strength, container, false);
        listView = view.findViewById(R.id.listview);
        CreateDatabase();

        getID process = new getID();
        process.execute();
        return view;
    }



    //Using GET request method with authentication token
    public class getID extends AsyncTask<Void,Void,Void> {
        String data ="";
        String singleParsed ="";


        protected Void doInBackground(Void... voids) {

            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://wger.de/api/v2/set/");
                //URL url = new URL("https://wger.de/api/v2/ingredient/");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Vary", "Accept");
                connection.setRequestProperty("Authorization", "Token 8c12ce416c7888affbc0a61b10866db5295b655e");
                connection.setUseCaches(false);
                connection.setReadTimeout(15 * 1000);
                connection.connect();
                InputStream inputStream =   connection.getInputStream();
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
                    JSONArray ja2 = jo.getJSONArray("exercises");
                    singleParsed = valueOf(ja2.getInt(0));
                    idList.add(singleParsed);
                }

                for (int i =0 ; i<6; i++){
                    try {
                        JSONObject objectID = array.getJSONObject(i);
                        String id = valueOf(objectID.get("id"));
                        String name = valueOf(objectID.get("name"));
                        System.out.println(idList.get(i));
                        if (idList.get(i).equals(id)){
                            arrayList.add(name);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
            protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);

            }
        }

    public void CreateDatabase (){
        //dummy database

        try {
            idObj.put("id","192");
            idObj.put("name","Bench Press");
            array.put(idObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            idObj1.put("id","148");
            idObj1.put("name","Lateral Raise");
            array.put(idObj1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            idObj2.put("id","145");
            idObj2.put("name","Fly with Dumbbells");
            array.put(idObj2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            idObj3.put("id","210");
            idObj3.put("name","Incline Dumbbell Press");
            array.put(idObj3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            idObj4.put("id","84");
            idObj4.put("name","French Press");
            array.put(idObj4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            idObj5.put("id","89");
            idObj5.put("name","Triceps Extension");
            array.put(idObj5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }







