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
import com.example.adocao.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CaoActivity extends AppCompatActivity {

    private TextView nomeCaoAumigo;
    private TextView corCaoAumigo;
    private TextView porteCaoAumigo;
    private TextView sexoCaoAumigo;
    private TextView racaCaoAumigo;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReferenceCao;
    private Button btHistoricoCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cao);

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceCao = FirebaseDatabase.getInstance().getReference("caes");
        iniciarComponentes();
        carregarDadosCao();

        btHistoricoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaoActivity.this, HistoricoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void carregarDadosCao() {
        String userIdCao = mAuth.getCurrentUser().getUid();

        databaseReferenceCao.child(userIdCao).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    CaoModel caoModel = snapshot.getValue(CaoModel.class);

                    if(caoModel != null){
                        nomeCaoAumigo.setText(caoModel.getNomeDoCao());
                        corCaoAumigo.setText(caoModel.getCor());
                        porteCaoAumigo.setText(caoModel.getPorte());
                        sexoCaoAumigo.setText(caoModel.getSexo());
                        racaCaoAumigo.setText(caoModel.getRaca());
                    }
                } else {
                    Toast.makeText(CaoActivity.this, "Dados do histórico não encontrado!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CaoActivity.this, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniciarComponentes(){
        nomeCaoAumigo = findViewById(R.id.nomeCaoAumigo);
        corCaoAumigo = findViewById(R.id.corCaoAumigo);
        porteCaoAumigo = findViewById(R.id.porteCaoAumigo);
        sexoCaoAumigo = findViewById(R.id.sexoCaoAumigo);
        racaCaoAumigo = findViewById(R.id.racaCaoAumigo);
        btHistoricoCao = findViewById(R.id.btHistoricoCao);
    }
}