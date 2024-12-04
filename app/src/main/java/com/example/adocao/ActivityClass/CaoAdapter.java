package com.example.adocao.ActivityClass;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adocao.Model.CaoModel;
import com.example.adocao.R;
import java.util.List;

public class CaoAdapter extends RecyclerView.Adapter {

    List<CaoModel> caes;

    public CaoAdapter(List<CaoModel> caes) {
        this.caes = caes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cao, parent, false);
        ViewHolderClass vhClass = new ViewHolderClass(view);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        CaoModel caoModel = caes.get(position);
        vhClass.nomeCaoItem.setText(caoModel.getNomeDoCao());
        vhClass.porteCaoItem.setText(caoModel.getPorte());
        vhClass.sexoCaoItem.setText(caoModel.getSexo());

        vhClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CaoActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return caes.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView nomeCaoItem;
        TextView porteCaoItem;
        TextView sexoCaoItem;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            nomeCaoItem = itemView.findViewById(R.id.nomeCaoItem);
            porteCaoItem = itemView.findViewById(R.id.porteCaoItem);
            sexoCaoItem = itemView.findViewById(R.id.sexoCaoItem);

        }
    }
}
