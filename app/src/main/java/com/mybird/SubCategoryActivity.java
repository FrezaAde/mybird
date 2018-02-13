package com.mybird;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")

public class SubCategoryActivity extends ActionBarActivity {

//    public SharedPreferences settings;
//    public ConnectionDetector cd;
//    static ArrayList<HashMap<String, String>> products_array;
//    ProductsAdapter adapter;
//    ListView products_listview;
//    DisplayImageOptions options;
//    ImageLoaderConfiguration imgconfig;
//    ProgressDialog dialog;
//
//    TextView txtcount;
//
//    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
//    HashMap<String, String>  catMap;
//    @SuppressWarnings("unchecked")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        settings = getSharedPreferences(ConstValue.MAIN_PREF, 0);
//        cd = new ConnectionDetector(getApplicationContext());
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_products);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        settings = getSharedPreferences(ConstValue.MAIN_PREF, 0);
//        cd=new ConnectionDetector(this);
//
//        File cacheDir = StorageUtils.getCacheDirectory(this);
//        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.loading)
//                .showImageForEmptyUri(R.drawable.loading)
//                .showImageOnFail(R.drawable.loading)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
//                .displayer(new SimpleBitmapDisplayer())
//                .imageScaleType(ImageScaleType.NONE)
//                .build();
//
//        imgconfig = new ImageLoaderConfiguration.Builder(this)
//                .build();
//        ImageLoader.getInstance().init(imgconfig);
//
//        ArrayList<HashMap<String,String>> categoryArray = new ArrayList<HashMap<String,String>>();
//        try {
//            categoryArray = (ArrayList<HashMap<String,String>>) ObjectSerializer.deserialize(settings.getString("categoryname", ObjectSerializer.serialize(new ArrayList<HashMap<String,String>>())));
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        catMap = new HashMap<String, String>();
//        catMap = categoryArray.get(getIntent().getExtras().getInt("position"));
//
//        products_array = new ArrayList<HashMap<String,String>>();
//        try {
//            products_array = (ArrayList<HashMap<String,String>>) ObjectSerializer.deserialize(settings.getString("products_"+catMap.get("id"), ObjectSerializer.serialize(new ArrayList<HashMap<String,String>>())));
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        products_listview = (ListView)findViewById(R.id.listView1);
//        adapter = new ProductsAdapter(getApplicationContext(), products_array);
//        products_listview.setAdapter(adapter);
//
//        TextView txtTitle = (TextView)findViewById(R.id.catname);
//        txtTitle.setText(catMap.get("name"));
//
//        txtcount = (TextView)findViewById(R.id.textcount);
//
//
//        products_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    settings.edit().putString(getString(R.string.productsActivity),ObjectSerializer.serialize(products_array.get(position))).commit();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                Intent intent = new Intent(SubCategoryActivity.this, ProductdetailActivity.class);
//                intent.putExtra("position", position);
//                startActivity(intent);
//            }
//        });
////        products_listview.setOnItemClickListener(new OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
////                                    long id) {
////                // TODO Auto-generated method stub
////
////                try {
////                    settings.edit().putString(getString(R.string.productsActivity),ObjectSerializer.serialize(products_array.get(position))).commit();
////                } catch (IOException e) {
////                    // TODO Auto-generated catch block
////                    e.printStackTrace();
////                }
////
////                Intent intent = new Intent(SubCategoryActivity.this, ProductdetailActivity.class);
////                intent.putExtra("position", position);
////                startActivity(intent);
////
////            }
////        });
//
//
//        new loadProductsTask().execute(true);
//
//
//
//
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//
//        handleIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            if (!query.equalsIgnoreCase("")) {
//                //use the query to search your data somehow
//                new loadProductsTask().execute(true);
//            }
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.products, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            Intent intent = new Intent(SubCategoryActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
//        else if(id==R.id.action_viewcart){
//            Intent intent = new Intent(SubCategoryActivity.this,ViewcartActivity.class);
//            startActivity(intent);
//
//        }
//        else if(id == android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    public class loadProductsTask extends AsyncTask<Boolean, Void, ArrayList<HashMap<String, String>>> {
//
//        JSONParser jParser;
//        JSONObject json;
//        String count;
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
//                settings.edit().putString("products_"+catMap.get("id"),ObjectSerializer.serialize(products_array)).commit();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            txtcount.setText(count);
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
//                    String urlstring = ConstValue.JSON_PRODUCTS+"&id="+catMap.get("id");
//
//                    if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
//                        String query = getIntent().getStringExtra(SearchManager.QUERY);
//                        //use the query to search your data somehow
//                        urlstring = urlstring + "&search="+query;
//                    }
//
//                    json = jParser.getJSONFromUrl(urlstring);
//                    count = json.getString("count");
//                    if (json.has("data")) {
//                        if(json.get("data") instanceof JSONArray){
//                            JSONArray jsonDrList = json.getJSONArray("data");
//                            products_array.clear();
//                            for (int i = 0; i < jsonDrList.length(); i++) {
//                                JSONObject obj = jsonDrList.getJSONObject(i);
//                                put_object(obj);
//                            }
//                        }else if(json.get("data") instanceof JSONObject){
//                            put_object(json.getJSONObject("data"));
//                        }
//                    }
//                }else
//                {
//                    Toast.makeText(SubCategoryActivity.this,getString(R.string.internetconnection), Toast.LENGTH_LONG).show();
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
//        public void put_object(JSONObject obj){
//            HashMap<String, String> map = new HashMap<String, String>();
//
//
//            try {
//                map.put("id", obj.getString("id"));
//                map.put("title", obj.getString("title"));
//                map.put("slug", obj.getString("slug"));
//                map.put("description", obj.getString("description"));
//                map.put("image", obj.getString("image"));
//
//                map.put("price", obj.getString("price"));
//                map.put("currency", obj.getString("currency"));
//                map.put("discount", obj.getString("discount"));
//                map.put("cod", obj.getString("cod"));
//                map.put("emi", obj.getString("emi"));
//                map.put("status", obj.getString("status"));
//
//                map.put("gmqty", obj.getString("gmqty"));
//                map.put("unit", obj.getString("unit"));
//                map.put("deliverycharge", obj.getString("deliverycharge"));
//                map.put("tax", obj.getString("tax"));
//                map.put("category_id", obj.getString("category_id"));
//                map.put("id_sub", obj.getString("id_sub"));
//
//                map.put("on_date", obj.getString("on_date"));
//
//                map.put("stock", obj.getString("stock"));
//                map.put("type", obj.getString("type"));
//                map.put("total_qty_stock", obj.getString("total_qty_stock"));
//                map.put("consume_qty_stock", obj.getString("consume_qty_stock"));
//
//                products_array.add(map);
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }

    public SharedPreferences settings;
    public ConnectionDetector cd;
    static ArrayList<HashMap<String, String>> subCategoryArray;
    SubCategoryAdapter adapter;
    ListView subCategory_listview;
    ProgressDialog dialog;

    HashMap<String, String>  catMap;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settings = getSharedPreferences(ConstValue.MAIN_PREF, 0);
        cd = new ConnectionDetector(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sub_category);
        getSupportActionBar().hide();


        ImageView backsub = (ImageView) findViewById(R.id.backSub);
        backsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        settings = getSharedPreferences(ConstValue.MAIN_PREF, 0);
        cd=new ConnectionDetector(this);




        ArrayList<HashMap<String,String>> categoryArray = new ArrayList<HashMap<String,String>>();
        try {
            categoryArray = (ArrayList<HashMap<String,String>>) ObjectSerializer.deserialize(settings.getString("categoryname", ObjectSerializer.serialize(new ArrayList<HashMap<String,String>>())));
        }catch (IOException e) {
            e.printStackTrace();
        }

//        catMap = new HashMap<String, String>();
//        catMap = categoryArray.get(getIntent().getExtras().getInt("id_kategori"));

        subCategoryArray = new ArrayList<HashMap<String,String>>();
//        try {
//            subCategoryArray = (ArrayList<HashMap<String,String>>) ObjectSerializer.deserialize(settings.getString("subCategory"+catMap.get("id"), ObjectSerializer.serialize(new ArrayList<HashMap<String,String>>())));
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

        subCategory_listview = (ListView)findViewById(R.id.listSubCategory);
     //   adapter = new SubCategoryAdapter(getApplicationContext(), subCategoryArray);
        subCategory_listview.setAdapter(adapter);


        subCategory_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    settings.edit().putString(getString(R.string.productsActivity),ObjectSerializer.serialize(subCategoryArray.get(position))).commit();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }


                Intent intent = new Intent(SubCategoryActivity.this, ProductsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });



        new loadSubCategoryTask().execute(true);


    }



    public class loadSubCategoryTask extends AsyncTask<Boolean, Void, ArrayList<HashMap<String, String>>> {

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

            }
            try {
                settings.edit().putString("subCategory",ObjectSerializer.serialize(subCategoryArray)).commit();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            adapter.notifyDataSetChanged();
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


            try {
                jParser = new JSONParser();

                if(cd.isConnectingToInternet())
                {

//
//                    int ints = new Intent().getExtras().getInt("position");

                    String urlstring = ConstValue.JSON_SUB_CATEGORY + "&idkategori=" + "5";
//
                    json = jParser.getJSONFromUrl(urlstring);

                    if (json.has("data")) {

                        if(json.get("data") instanceof JSONArray) {
                            JSONArray jsonDrList = json.getJSONArray("data");
                            subCategoryArray.clear();


                            for (int i = 0; i < jsonDrList.length(); i++) {
                                JSONObject obj = jsonDrList.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();

                                map.put("id", obj.getString("id"));
                                map.put("id_kategori", obj.getString("id_kategori"));
                                map.put("id_sub_cat", obj.getString("id_sub_cat"));
                                map.put("nama_sub", obj.getString("nama_sub"));
                                map.put("name", obj.getString("name"));
                                map.put("slug", obj.getString("slug"));
                                map.put("description", obj.getString("description"));
                                map.put("icon", obj.getString("icon"));
                                map.put("status", obj.getString("status"));


                                Toast.makeText(SubCategoryActivity.this, obj.getString("nama"), Toast.LENGTH_LONG).show();

                                subCategoryArray.add(map);



                                String idsub = obj.getString("name");
                                String idkat = obj.getString("id_kategori");

                                Log.d("Response" , idsub);

                                Toast.makeText(SubCategoryActivity.this,"Cek" + idsub, Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                }else
                {
                    Toast.makeText(SubCategoryActivity.this,getString(R.string.internetconnection), Toast.LENGTH_LONG).show();
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

}

