package com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context context;
    private List<Category> listCategory;
    private CatergorisFragment onItemClickListener;

    public CategoriesAdapter(List<Category> listCategory,Context context) {
        this.listCategory = listCategory;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categories, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.tv_name.setText(this.categories.get(position).getName());
        holder.tv_name.setText(listCategory.get(position).getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickSelectedItem(listCategory.get(position));
            }
        });
        Log.i("TAG","--->"+listCategory.get(position).getImagen());
        Glide.with(context)
                .load(listCategory.get(position).getImagen())
                .apply(new RequestOptions().placeholder(R.drawable.doctor).error(R.drawable.kids))
                .into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
        if (listCategory == null)
            return 0;
        else
            return listCategory.size();
    }

    public void setOnItemClickListener(CatergorisFragment onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        void onClickSelectedItem(Category category);
    }

}