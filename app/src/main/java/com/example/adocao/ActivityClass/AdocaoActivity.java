package com.example.adocao.ActivityClass;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adocao.Model.CaoModel;
import com.example.adocao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdocaoActivity extends AppCompatActivity {

    private ImageButton btPerfil;
    private ImageButton btAdd;
    private Button btVer;
    List<CaoModel> caes;
    RecyclerView recyclerView;
    CaoAdapter caoAdapter;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adocao);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        caes = new ArrayList<>();
        caoAdapter = new CaoAdapter(caes);
        recyclerView.setAdapter(caoAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("caes").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (DataSnapshot data : task.getResult().getChildren()) {

                    CaoModel cao = data.getValue(CaoModel.class);

                    if (cao != null) {
                        caes.add(cao);
                    }
                }
                caoAdapter.notifyDataSetChanged();
            }
        });


        btPerfil = findViewById(R.id.btPerfil);
        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdocaoActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdocaoActivity.this, CadastrarCaoActivity.class);
                startActivity(intent);
            }
        });


    }
}

