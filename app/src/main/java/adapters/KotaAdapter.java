package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybird.CobaProfilActivity;
import com.mybird.KotaActivity;
import com.mybird.MainActivity;
import com.mybird.R;

import java.util.List;

import model.Ongkir;

/**
 * Created by canc on 2/12/2018.
 */

public class KotaAdapter extends RecyclerView.Adapter<KotaAdapter.OngkirViewHolder> {

    private List<Ongkir> ongkirs;
    private int rowLayout;
    private Context context;
    KotaActivity kotaActivity;

    public class OngkirViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linear_data;
        TextView tvData;

        public OngkirViewHolder(View v) {
            super(v);
            linear_data = (LinearLayout) v.findViewById(R.id.linear_data);
            tvData = (TextView) v.findViewById(R.id.tvData);
        }
    }

    public KotaAdapter(List<Ongkir> ongkirs, int rowLayout, Context context, KotaActivity kotaActivity) {
        this.ongkirs = ongkirs;
        this.rowLayout = rowLayout;
        this.context = context;
        this.kotaActivity = kotaActivity;
    }

    @Override
    public KotaAdapter.OngkirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new OngkirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KotaAdapter.OngkirViewHolder holder, final int position) {
        holder.tvData.setText(ongkirs.get(position).city_name);

        holder.tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CobaProfilActivity.class);
                i.putExtra("city_id", String.valueOf(ongkirs.get(position).city_id));
                i.putExtra("city", String.valueOf(ongkirs.get(position).city_name));
                i.putExtra("province", String.valueOf(kotaActivity.getProvince()));
                i.putExtra("name", kotaActivity.getName());
                i.putExtra("username", kotaActivity.getUsername());
                i.putExtra("email", kotaActivity.getEmail());
                i.putExtra("telepon", kotaActivity.getTelepon());
                i.putExtra("alamat", kotaActivity.getAlamat());
                i.putExtra("kodepos", kotaActivity.getKodepos());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ongkirs.size();
    }


}
