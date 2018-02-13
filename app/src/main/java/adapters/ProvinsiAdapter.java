package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybird.KotaActivity;
import com.mybird.ProvinsiActivity;
import com.mybird.R;

import java.util.List;

import model.Ongkir;

/**
 * Created by canc on 2/12/2018.
 */

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.OngkirViewHolder>{

    private List<Ongkir> ongkirs;
    private int rowLayout;
    private Context context;
    ProvinsiActivity provinsiActivity;

    public static class OngkirViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear_data;
        TextView tvData;

        public OngkirViewHolder(View v) {
            super(v);
            LinearLayout linear_data = (LinearLayout) v.findViewById(R.id.linear_data);
            TextView tvData = (TextView) v.findViewById(R.id.tvData);
        }
    }

    public ProvinsiAdapter(List<Ongkir> ongkirs, int rowLayout, Context context , ProvinsiActivity provinsiActivity) {
        this.ongkirs = ongkirs;
        this.rowLayout = rowLayout;
        this.context = context;
        this.provinsiActivity = provinsiActivity;
    }

    @Override
    public OngkirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent, false);
        return new OngkirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OngkirViewHolder holder, final int position) {
        holder.tvData.setText(ongkirs.get(position).province);

        holder.linear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), KotaActivity.class);
                i.putExtra("province_id", String.valueOf(ongkirs.get(position).province_id));
                i.putExtra("province", String.valueOf(ongkirs.get(position).province));
                i.putExtra("name", provinsiActivity.getName());
                i.putExtra("username", provinsiActivity.getUsername());
                i.putExtra("email", provinsiActivity.getEmail());
                i.putExtra("telepon", provinsiActivity.getTelepon());
                i.putExtra("alamat", provinsiActivity.getAlamat());
                i.putExtra("kodepos", provinsiActivity.getKodepos());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ongkirs.size();
    }
}
