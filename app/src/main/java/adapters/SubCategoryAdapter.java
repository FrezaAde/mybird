package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mybird.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SubCategoryAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<HashMap<String, String>> postItems;
    public SharedPreferences settings;
    public final String PREFS_NAME = "SubCategory";



    public SubCategoryAdapter(Context context, ArrayList<HashMap<String, String>> arraylist){
        this.context = context;

        postItems = arraylist;
        settings = context.getSharedPreferences(PREFS_NAME, 0);

    }




    @Override
    public int getCount() {
        return postItems.size();
    }
    @Override
    public Object getItem(int position) {
        return postItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_subcategory, null);
        }
        final  HashMap<String, String> map = postItems.get(position);


        TextView txtTitle = (TextView)convertView.findViewById(R.id.nameSub);
         txtTitle.setText(map.get("nama_sub"));

        return convertView;
    }



}

