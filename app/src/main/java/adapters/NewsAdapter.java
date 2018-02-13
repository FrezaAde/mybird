package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mybird.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import Config.ConstValue;
import imgLoader.AnimateFirstDisplayListener;

/**
 * Created by canc on 15/10/2017.
 */

public class NewsAdapter extends BaseAdapter {
    private ImageLoadingListener animateFirstListener =new AnimateFirstDisplayListener();

    private Context context;
    private ArrayList<HashMap<String, String>> postItems;
    public SharedPreferences settings;
    public final String PREF_NAME = "News";

    DisplayImageOptions options;
    ImageLoaderConfiguration imgconfig;

    public NewsAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;

        File cacheDir = StorageUtils.getCacheDirectory(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.loading)
                .showImageOnFail(R.drawable.loading)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        imgconfig = new ImageLoaderConfiguration.Builder(context)
                .build();
        ImageLoader.getInstance().init(imgconfig);

        postItems = arrayList;
        settings = context.getSharedPreferences(PREF_NAME, 0);

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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_news, null);
        }
        final HashMap<String, String> map = postItems.get(position);

        ImageView imgProduct = (ImageView)convertView.findViewById(R.id.newsimage);
        ImageLoader.getInstance().displayImage(ConstValue.NEWS_IMAGE_SMALL_PATH+map.get("image"), imgProduct, options, animateFirstListener);
//        Toast.makeText(context, "Image Loaders : " + imgProduct, Toast.LENGTH_SHORT).show();
        TextView txtTitle = (TextView)convertView.findViewById(R.id.newsTitle);
        txtTitle.setText(map.get("title"));

        return convertView;
    }
}
