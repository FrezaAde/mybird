package adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mybird.R;
import com.mybird.SubCatModel;

import java.util.ArrayList;

/**
 * Created by LENOVO on 08/11/2017.
 */

public class SubCatAdapter implements ListAdapter {
    public SharedPreferences settings;
    public final String PREFS_NAME = "SubCategory";



    private Context context;
    private ArrayList<SubCatModel> players;




    public SubCatAdapter(Context context,  ArrayList<SubCatModel> playersList) {
        super();

        this.context = context;
        this.players = playersList;
    }

    public SubCatAdapter(Context applicationContext, String namaSub) {
        this.context = context;
//        this.players = namaSub;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }
    @Override
    public int getCount() {
        return players.size();
    }
    @Override
    public Object getItem(final int position) {
        return null;
    }

    @Override
    public long getItemId(final int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.row_subcategory, null);


            TextView playerName = (TextView) convertView.findViewById(R.id.nameSub);
            playerName.setText((CharSequence) players.get(position));


        }


        return convertView;

    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }
}
