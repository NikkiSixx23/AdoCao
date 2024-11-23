package com.example.adocao.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adocao.Model.CaoModel;
import com.example.adocao.Model.UsuarioModel;
import com.example.adocao.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoricoActivity extends AppCompatActivity {

    private TextView descricaoHistorico;
    private Button btAdocao;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReferenceCaes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historico);

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceCaes = FirebaseDatabase.getInstance().getReference("caes");
        iniciarComponentes();
        carregarDadosCao();


        btAdocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoricoActivity.this, AdotadoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void carregarDadosCao() {
        String userIdCao = mAuth.getCurrentUser().getUid();

        databaseReferenceCaes.child(userIdCao).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    CaoModel caoModel = snapshot.getValue(CaoModel.class);

                    if(caoModel != null){
                        descricaoHistorico.setText(caoModel.getHistorico());
                    }

                } else {
                    Toast.makeText(HistoricoActivity.this, "Dados do histórico não encontrado!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HistoricoActivity.this, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniciarComponentes(){
        descricaoHistorico = findViewById(R.id.descricaoHistorico);
        btAdocao =  findViewById(R.id.btAdocao);
    }
}