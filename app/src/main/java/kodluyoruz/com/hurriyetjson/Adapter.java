package kodluyoruz.com.hurriyetjson;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.picasso.Picasso;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    List<InfoViewModel> listem;
    Activity activity;

    Context context;

    public Adapter(List<InfoViewModel> liste, Activity activity) {
        this.listem = liste;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoViewModel ınfoViewModel = listem.get(position);


        holder.haberBaslik.setText(ınfoViewModel.getTitle().toString());
        holder.haberIcerik.setText(ınfoViewModel.getDescription());

        List<File> files = listem.get(position).getFiles();

        Picasso.with(activity).load(files.get(position).getFileUrl());





//        holder.haberIcerik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return  listem.size();
    }
}
