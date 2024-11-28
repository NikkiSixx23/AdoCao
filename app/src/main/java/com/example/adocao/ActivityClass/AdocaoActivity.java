package com.example.adocao.ActivityClass;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adocao.Model.CaoModel;
import com.example.adocao.R;
import java.util.ArrayList;
import java.util.List;

public class AdocaoActivity extends AppCompatActivity {

    private ImageButton btPerfil;
    private ImageButton btAdd;
    private RecyclerView recyclerViewCao;
    private List<CaoModel> listaCao;
    private RecyclerCaoAdapter adapterCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adocao);

        listaCao = new ArrayList<>();
        recyclerViewCao = findViewById(R.id.recyclerViewCao);
        recyclerViewCao.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCao.scrollToPosition(0);
        adapterCao = new RecyclerCaoAdapter(listaCao);
        recyclerViewCao.setAdapter(adapterCao);

        btPerfil = findViewById(R.id.btPerfil);
        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdocaoActivity.this, PerfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdocaoActivity.this, CadastrarCaoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}