package com.example.tecomca.mylogin_seccion05.Fragments.Juegos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tecomca.mylogin_seccion05.Model.Games;
import com.example.tecomca.mylogin_seccion05.Model.Juegos;
import com.example.tecomca.mylogin_seccion05.R;

import java.util.List;

public class ListaJuegosAdapter extends RecyclerView.Adapter<ListaJuegosAdapter.ViewHolder> {

    private Context context;
    private List<Games> listJuegos;
    private ListaJuegosFragment onItemClickListener;

    public ListaJuegosAdapter(List<Games> listCategory, Context context) {
        this.listJuegos = listCategory;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaJuegosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categories, viewGroup, false);
        return new ListaJuegosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.tv_name.setText(this.categories.get(position).getName());
        holder.tv_name.setText(listJuegos.get(position).getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickSelectedItem(listJuegos.get(position));
            }
        });
        Log.i("TAG","--->"+listJuegos.get(position).getImage());
        Glide.with(context)
                .load(listJuegos.get(position).getImage())
                .apply(new RequestOptions().placeholder(R.drawable.doctor).error(R.drawable.kids))
                .into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
            return listJuegos.size();
    }

    public void setOnItemClickListener(ListaJuegosFragment onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateAll(List<Games> update) {
        //Log.i(TAG,"--->updateAll "+ update.size());
        listJuegos.clear();
        listJuegos.addAll(listJuegos.size(), update);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout item;
        TextView tv_name;
        ImageView iv_logo;

        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item);
            tv_name = view.findViewById(R.id.tv_name);
            iv_logo = view.findViewById(R.id.iv_image);
        }

    }

    public interface OnItemClickListener{
        void onClickSelectedItem(Games juegos);
    }

}
