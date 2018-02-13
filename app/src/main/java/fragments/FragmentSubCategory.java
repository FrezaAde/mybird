package fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mybird.ProductsActivity;
import com.mybird.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Config.ConstValue;
import adapters.SubCategoryAdapter;
import imgLoader.JSONParser;
import util.ConnectionDetector;
import util.ObjectSerializer;

/**
 * Created by canc on 20/10/2017.
 */

public class FragmentSubCategory extends Fragment {
    Activity act;
    public SharedPreferences settings;
    public ConnectionDetector cd;
    SubCategoryAdapter adapter;
    ArrayList<HashMap<String, String>> subCategoryArray;
    HashMap<String, String> site_settngs;
    ListView listView;


    @SuppressWarnings("unchecked")
    @SuppressLint("NewApi") @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sub_category, container, false);
        act = getActivity();

        settings = act.getSharedPreferences(ConstValue.MAIN_PREF, 0);
        cd = new ConnectionDetector(act);

        subCategoryArray = new ArrayList<HashMap<String, String>>();
        try {
            subCategoryArray = (ArrayList<HashMap<String, String>>) ObjectSerializer.deserialize(settings.getString("namesub", ObjectSerializer.serialize(new ArrayList<HashMap<String, String>>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView = (ListView)rootView.findViewById(R.id.listSubCategory);
 //       adapter = new SubCategoryAdapter(act, subCategoryArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(act, ProductsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        new loadSubCategory().execute(true);


        return rootView;
    }

    public class loadSubCategory extends AsyncTask<Boolean, Void, ArrayList<HashMap<String, String>>> {

        JSONParser jParser;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {

            if (result!=null) {

            }
            try {
                settings.edit().putString("namesub",ObjectSerializer.serialize(subCategoryArray)).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }


            adapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(ArrayList<HashMap<String, String>> result) {
            super.onCancelled(result);
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(
                Boolean... params) {

            try {
                jParser = new JSONParser();

                if (cd.isConnectingToInternet()) {

                    String urlstring = ConstValue.JSON_SUB_CATEGORY;

                    json = jParser.getJSONFromUrl(urlstring);
                    if (json.has("data")) {

                        if (json.get("data") instanceof JSONArray) {

                            JSONArray jsonDrList = json.getJSONArray("data");

                            subCategoryArray.clear();

                            for (int i = 0; i < jsonDrList.length(); i++) {
                                JSONObject obj = jsonDrList.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();

                                map.put("id", obj.getString("id"));
                                map.put("name_sub", obj.getString("name_sub"));
                                map.put("name", obj.getString("name"));
                                map.put("slug", obj.getString("slug"));
                                map.put("description", obj.getString("description"));
                                map.put("icon", obj.getString("icon"));

                                subCategoryArray.add(map);

                            }
                        }
                    }
                } else {
                    Toast.makeText(act, getString(R.string.internetconnection), Toast.LENGTH_SHORT).show();
                }

                jParser = null;
                json = null;
            } catch (Exception e) {
                return null;
            }

            return null;
        }
    }


}
