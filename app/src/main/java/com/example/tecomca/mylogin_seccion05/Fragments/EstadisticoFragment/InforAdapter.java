package com.example.tecomca.mylogin_seccion05.Fragments.EstadisticoFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tecomca.mylogin_seccion05.Fragments.InstructionsFragment.AlertAdapter;
import com.example.tecomca.mylogin_seccion05.Fragments.InstructionsFragment.AlertFragment;
import com.example.tecomca.mylogin_seccion05.Model.Instructions;
import com.example.tecomca.mylogin_seccion05.Model.Stadistics;
import com.example.tecomca.mylogin_seccion05.R;

import java.util.List;


public class InforAdapter extends RecyclerView.Adapter<InforAdapter.ViewHolder> {

    private Context context;
    private List<Stadistics> listStadistics;
    private InforFragment onItemClickListener;

    public InforAdapter(List<Instructions> listInstruction, Context context) {
        this.listStadistics = listStadistics;
        this.context = context;
    }

    @NonNull
    @Override
    public InforAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stadistics, viewGroup, false);
        return new InforAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InforAdapter.ViewHolder holder, final int position) {
//        holder.tv_name.setText(this.categories.get(position).getName());
        holder.tv_name.setText(listStadistics.get(position).getBuenas());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickSelectedItem(listStadistics.get(position));
            }
        });
        //Log.i("TAG","--->"+listStadistics.get(position).getImagen());
//        Glide.with(context)
//                .load(listStadistics.get(position).getImagen())
//                .apply(new RequestOptions().placeholder(R.drawable.doctor).error(R.drawable.kids))
//                .into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
        if (listStadistics == null)
            return 0;
        else
            return listStadistics.size();
    }

    public void setOnItemClickListener(InforFragment onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout card;
        TextView tv_name;
        TextView tv_buena;
//        ImageView iv_logo;

        public ViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.card_view);
            tv_name = view.findViewById(R.id.textViewName);
            tv_buena = view.findViewById(R.id.textViewBuenas);
        }

    }

    public interface OnItemClickListener{
        void onClickSelectedItem(Stadistics stadistics);
    }
    
}