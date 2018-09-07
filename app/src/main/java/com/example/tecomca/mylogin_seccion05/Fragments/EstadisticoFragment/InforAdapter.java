package com.example.tecomca.mylogin_seccion05.Fragments.EstadisticoFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
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

import butterknife.BindView;
import butterknife.ButterKnife;


public class InforAdapter extends RecyclerView.Adapter<InforAdapter.ViewHolder> {

    private Context context;
    private List<Stadistics> listStadistics;
    private InforFragment onItemClickListener;

    public InforAdapter(List<Stadistics> listStadistics, Context context) {
        this.listStadistics = listStadistics;
        this.context = context;
    }

    @NonNull
    @Override
    public InforAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stadistics, viewGroup, false);
        Log.i("TAG","Hola en el onCreateViewHolder");
        return new InforAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull InforAdapter.ViewHolder holder, final int position) {
//        holder.tv_name.setText(this.categories.get(position).getName());
        holder.tv_name.setText(listStadistics.get(position).getNamePlayer());
        holder.tv_game_name.setText(listStadistics.get(position).getNameGame());
        holder.tv_buena.setText(String.valueOf(listStadistics.get(position).getBuenas()));
        holder.tv_mala.setText(String.valueOf(listStadistics.get(position).getMalas()));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickSelectedItem(listStadistics.get(position));
            }
        });
        Glide.with(context)
                .load(R.id.imageViewBackground)
                .apply(new RequestOptions().placeholder(R.drawable.carga).error(R.drawable.stadistics))
                .into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
            return listStadistics.size();
    }

    public void setOnItemClickListener(InforFragment onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView item;
        @BindView(R.id.textViewName)
        TextView tv_name;
        @BindView(R.id.textViewNameGame)
        TextView tv_game_name;
        @BindView(R.id.textViewBuenas)
        TextView tv_buena;
        @BindView(R.id.textViewMalas)
        TextView tv_mala;
        @BindView(R.id.imageViewBackground)
        ImageView iv_logo;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public interface OnItemClickListener{
        void onClickSelectedItem(Stadistics stadistics);
    }
    
}
