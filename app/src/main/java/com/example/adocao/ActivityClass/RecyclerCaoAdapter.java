package com.example.adocao.ActivityClass;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adocao.Model.CaoModel;
import com.example.adocao.R;

import java.util.List;

public class RecyclerCaoAdapter extends RecyclerView.Adapter<RecyclerCaoAdapter.ViewHolder> {

    private List<CaoModel> caoModel;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView caoImageRecycler;
        private TextView nomeCaoRecycler;
        private TextView sexoCaoRecycler;
        private TextView porteCaoRecycler;

        public ViewHolder(@NonNull View view) {
            super(view);
            caoImageRecycler = view.findViewById(R.id.caoImageRecycler);
            nomeCaoRecycler = view.findViewById(R.id.nomeDoCaoRecycler);
            sexoCaoRecycler = view.findViewById(R.id.sexoCaoRecycler);
            porteCaoRecycler = view.findViewById(R.id.porteCaoRecycler);
        }

        public ImageView getCaoImageRecycler() {
            return caoImageRecycler;
        }

        public TextView getNomeCaoRecycler() {
            return nomeCaoRecycler;
        }

        public TextView getSexoCaoRecycler() {
            return sexoCaoRecycler;
        }

        public TextView getPorteCaoRecycler() {
            return porteCaoRecycler;
        }
    }

    public RecyclerCaoAdapter(List caoModel) {
        this.caoModel = caoModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cao_recycler, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCaoImageRecycler().setImageResource(R.mipmap.swen);
        holder.getNomeCaoRecycler().setText(caoModel.get(position).getNomeDoCao());
        holder.getSexoCaoRecycler().setText(caoModel.get(position).getSexo());
        holder.getPorteCaoRecycler().setText(caoModel.get(position).getPorte());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return caoModel.size();
    }
}
