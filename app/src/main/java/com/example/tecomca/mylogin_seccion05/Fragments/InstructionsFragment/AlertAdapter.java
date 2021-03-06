package com.example.tecomca.mylogin_seccion05.Fragments.InstructionsFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CategoriesAdapter;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CatergorisFragment;
import com.example.tecomca.mylogin_seccion05.Model.Instructions;
import com.example.tecomca.mylogin_seccion05.R;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {

    private Context context;
    private List<Instructions> listInstruction;
    private AlertFragment onItemClickListener;

    public AlertAdapter(List<Instructions> listInstruction, Context context) {
        this.listInstruction = listInstruction;
        this.context = context;
    }


    @NonNull
    @Override
    public AlertAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_instructions, viewGroup, false);
        return new AlertAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.tv_name.setText(this.categories.get(position).getName());
        holder.tv_name.setText(listInstruction.get(position).getDescription());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickSelectedItem(listInstruction.get(position));
            }
        });
        Log.i("TAG","--->"+listInstruction.get(position).getImagen());
        Glide.with(context)
                .load(listInstruction.get(position).getImagen())
                .apply(new RequestOptions().placeholder(R.drawable.carga).error(R.drawable.advertencia))
                .into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
        if (listInstruction == null)
            return 0;
        else
            Log.i("TAG","--->"+listInstruction.size());
            return listInstruction.size();
    }

    public void setOnItemClickListener(AlertFragment onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView item;
        TextView tv_name;
        ImageView iv_logo;

        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item);
            tv_name = view.findViewById(R.id.tv_names);
            iv_logo = view.findViewById(R.id.iv_images);
        }

    }

    public interface OnItemClickListener{
        void onClickSelectedItem(Instructions instructions);
    }
}
