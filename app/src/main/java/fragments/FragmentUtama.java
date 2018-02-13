package fragments;

import imgLoader.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import util.ConnectionDetector;
import util.ObjectSerializer;
import Config.ConstValue;
import adapters.CategoryAdapter;
import adapters.SliderImageAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.mybird.NewsActivity;
import com.mybird.ProductsActivity;
import com.mybird.R;
import com.mybird.SubCategory2;
import com.mybird.SubCategoryActivity;

@SuppressLint("NewApi")
public class FragmentUtama extends Fragment {
    Activity act;
    public SharedPreferences settings;
    public ConnectionDetector cd;
    CategoryAdapter adapter;
    //SliderImageAdapter adapter1;
    ArrayList<HashMap<String, String>> categoryArray;
    ArrayList<HashMap<String, String>> slideryArray;
    HashMap<String, String> site_settings;
    PagerAdapter pageadapter;
    GridView listview;
    ImageView berita;
    ImageView cage;
    ImageView vitamin;
    ImageView bird;
    ImageView juara;
    ImageView accesoriess;
    ImageView lain;
    ViewPager viewPager;


    @SuppressWarnings("unchecked")
    @SuppressLint("NewApi") @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_utama, container, false);
        act =getActivity();


        //-----
        settings = act.getSharedPreferences(ConstValue.MAIN_PREF, 0);
        cd=new ConnectionDetector(act);


//        categoryArray = new ArrayList<HashMap<String,String>>();
//        try {
//            categoryArray = (ArrayList<HashMap<String,String>>) ObjectSerializer.deserialize(settings.getString("categoryname", ObjectSerializer.serialize(new ArrayList<HashMap<String,String>>())));
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
        berita = (ImageView) rootView.findViewById(R.id.berita);
        cage = (ImageView) rootView.findViewById(R.id.cage);
        vitamin = (ImageView) rootView.findViewById(R.id.vitamin);
        bird = (ImageView) rootView.findViewById(R.id.bird);
        accesoriess = (ImageView) rootView.findViewById(R.id.accesoriess);
        lain = (ImageView) rootView.findViewById(R.id.lain);
        juara = (ImageView) rootView.findViewById(R.id.juara);
//        adapter = new CategoryAdapter(act, categoryArray);
//        listview.setAdapter(adapter);


        slideryArray = new ArrayList<HashMap<String,String>>();
        try {
            slideryArray = (ArrayList<HashMap<String,String>>) ObjectSerializer.deserialize(settings.getString("imagename", ObjectSerializer.serialize(new ArrayList<HashMap<String,String>>())));
        }catch (IOException e) {
            e.printStackTrace();
        }
        viewPager = (ViewPager)rootView.findViewById(R.id.viewPager);


        berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        cage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SubCategory2.class);
                intent.putExtra("pos", "Sangkar");
                startActivity(intent);
            }
        });

        vitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SubCategory2.class);
                intent.putExtra("pos", "vitamin");
                startActivity(intent);
            }
        });

        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SubCategory2.class);
                intent.putExtra("pos", "Burung");
                startActivity(intent);
            }
        });

        accesoriess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SubCategory2.class);
                intent.putExtra("pos", "Aksesoris");
                startActivity(intent);
            }
        });

        lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SubCategory2.class);
                intent.putExtra("pos","Lain - lain");
                startActivity(intent);
            }
        });

        juara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDaftarJuara fragmentDaftarJuara = new FragmentDaftarJuara();
                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.sample_content_fragment, fragmentDaftarJuara).commit();
//                FragmentDaftarJuara juara = new FragmentDaftarJuara();
//                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//
//                fragmentTransaction.replace(R.id.fragmentUtama, juara).commit();
            }
        });




//        listview.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                //	ConstValue.selected_products_list = categoryArray;
//                Intent intent = new Intent(act, ProductsActivity.class);
//                intent.putExtra("position", 0);
//                startActivity(intent);
//            }
//        });
//
//        new load_settings().execute(true);
//        new loadCategoryTask().execute(true);
        new loadSliderTask().execute(true);


        return rootView;
    }

    public class loadSliderTask extends AsyncTask<Boolean, Void, ArrayList<HashMap<String, String>>> {

        JSONParser jParser;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            // TODO Auto-generated method stub
            if (result!=null) {
                //adapter.notifyDataSetChanged();
            }
            try {
                settings.edit().putString("imagename",ObjectSerializer.serialize(slideryArray)).commit();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            pageadapter = new SliderImageAdapter(act,slideryArray);
            viewPager.setAdapter(pageadapter);

            //adapter1.notifyDataSetChanged();
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(ArrayList<HashMap<String, String>> result) {
            // TODO Auto-generated method stub
            super.onCancelled(result);
        }


        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(
                Boolean... params) {
            // TODO Auto-generated method stub

            try {
                jParser = new JSONParser();

                if(cd.isConnectingToInternet())
                {
                    //	String query = "";

                    String urlstring = ConstValue.JSON_SLIDER_IMAGE;

                    json = jParser.getJSONFromUrl(urlstring);
                    if (json.has("data")) {

                        if(json.get("data") instanceof JSONArray){

                            JSONArray jsonDrList = json.getJSONArray("data");

                            slideryArray.clear();


                            for (int i = 0; i < jsonDrList.length(); i++) {
                                JSONObject obj = jsonDrList.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();


                                map.put("id", obj.getString("id"));

                                map.put("image", obj.getString("image"));
                                map.put("title", obj.getString("title"));
                                map.put("status", obj.getString("status"));

                                slideryArray.add(map);


                            }
                        }

                    }
                }else
                {
                    Toast.makeText(act, getString(R.string.loading), Toast.LENGTH_LONG).show();
                }

                jParser = null;
                json = null;

            } catch (Exception e) {
                // TODO: handle exception

                return null;
            }
            return null;
        }

    }


//    public class loadCategoryTask extends AsyncTask<Boolean, Void, ArrayList<HashMap<String, String>>> {
//
//        JSONParser jParser;
//        JSONObject json;
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
//            // TODO Auto-generated method stub
//            if (result!=null) {
//                //adapter.notifyDataSetChanged();
//            }
//            try {
//                settings.edit().putString("categoryname",ObjectSerializer.serialize(categoryArray)).commit();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//
//            adapter.notifyDataSetChanged();
//            super.onPostExecute(result);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            // TODO Auto-generated method stub
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onCancelled(ArrayList<HashMap<String, String>> result) {
//            // TODO Auto-generated method stub
//            super.onCancelled(result);
//        }
//
//
//        @Override
//        protected ArrayList<HashMap<String, String>> doInBackground(
//                Boolean... params) {
//            // TODO Auto-generated method stub
//
//            try {
//                jParser = new JSONParser();
//
//                if(cd.isConnectingToInternet())
//                {
//                    //	String query = "";
//
//                    String urlstring = ConstValue.JSON_CATEGORY;
//
//                    json = jParser.getJSONFromUrl(urlstring);
//                    if (json.has("data")) {
//
//                        if(json.get("data") instanceof JSONArray){
//
//                            JSONArray jsonDrList = json.getJSONArray("data");
//
//                            categoryArray.clear();
//
//
//                            for (int i = 0; i < jsonDrList.length(); i++) {
//                                JSONObject obj = jsonDrList.getJSONObject(i);
//                                HashMap<String, String> map = new HashMap<String, String>();
//
//
//                                map.put("id", obj.getString("id"));
//
//                                map.put("name", obj.getString("name"));
//                                map.put("slug", obj.getString("slug"));
//                                map.put("description", obj.getString("description"));
//                                map.put("icon", obj.getString("icon"));
//
//                                categoryArray.add(map);
//
//
//                            }
//                        }
//
//                    }
//                }else
//                {
//                    Toast.makeText(act,getString(R.string.internetconnection), Toast.LENGTH_LONG).show();
//                }
//
//                jParser = null;
//                json = null;
//
//            } catch (Exception e) {
//                // TODO: handle exception
//
//                return null;
//            }
//            return null;
//        }
//
//    }
//
//
//
//    public class load_settings extends AsyncTask<Boolean, Void, ArrayList<HashMap<String, String>>> {
//
//        JSONParser jParser;
//        JSONObject json;
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
//            // TODO Auto-generated method stub
//            if (result!=null) {
//                //adapter.notifyDataSetChanged();
//            }
//            try {
//                settings.edit().putString("site_settings",ObjectSerializer.serialize(site_settings)).commit();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//
//            adapter.notifyDataSetChanged();
//            super.onPostExecute(result);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            // TODO Auto-generated method stub
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onCancelled(ArrayList<HashMap<String, String>> result) {
//            // TODO Auto-generated method stub
//            super.onCancelled(result);
//        }
//
//
//        @Override
//        protected ArrayList<HashMap<String, String>> doInBackground(
//                Boolean... params) {
//            // TODO Auto-generated method stub
//
//            try {
//                jParser = new JSONParser();
//
//                if(cd.isConnectingToInternet())
//                {
//                    //	String query = "";
//
//                    String urlstring = ConstValue.JSON_SETTINGS;
//
//                    json = jParser.getJSONFromUrl(urlstring);
//                    if (json.has("data")) {
//
//                        if(json.get("data") instanceof JSONArray){
//
//                            JSONArray jsonDrList = json.getJSONArray("data");
//                            site_settings = new HashMap<String, String>();
//
//                            for (int i = 0; i < jsonDrList.length(); i++) {
//                                JSONObject obj = jsonDrList.getJSONObject(i);
//                                site_settings.put(obj.getString("title"), obj.getString("value"));
//
//                            }
//                        }
//
//                    }
//                }else
//                {
//                    Toast.makeText(act,getString(R.string.internetconnection), Toast.LENGTH_LONG).show();
//                }
//
//                jParser = null;
//                json = null;
//
//            } catch (Exception e) {
//                // TODO: handle exception
//
//                return null;
//            }
//            return null;
//        }
//
//    }



}
