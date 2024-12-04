package com.example.adocao.ActivityClass;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adocao.Model.CaoModel;
import com.example.adocao.Model.UsuarioModel;
import com.example.adocao.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdotadoActivity extends AppCompatActivity {

    private TextView nomeCaoFinal;
    private TextView responsavel;
    private TextView contatoFinal;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReferenceUsuario;
    private DatabaseReference databaseReferenceCaes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adotado);

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference("usuarios");
        databaseReferenceCaes = FirebaseDatabase.getInstance().getReference("caes");
        iniciarComponentes();
        carregarDadosUsuario();
        carregarDadosCao();

    }

    private void carregarDadosUsuario() {
        String userId = mAuth.getCurrentUser().getUid();

        databaseReferenceUsuario.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    UsuarioModel usuarioModel = snapshot.getValue(UsuarioModel.class);

                    if (usuarioModel != null) {
                        responsavel.setText(usuarioModel.getNome());
                        contatoFinal.setText(usuarioModel.getContato());
                    }

                } else {
                    Toast.makeText(AdotadoActivity.this, "Dados do usuário não encontrados!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdotadoActivity.this, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarDadosCao() {
        String userIdCao = mAuth.getCurrentUser().getUid();

        databaseReferenceCaes.child(userIdCao).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    CaoModel caoModel = snapshot.getValue(CaoModel.class);

                    if (caoModel != null) {
                        nomeCaoFinal.setText(caoModel.getNomeDoCao());
                    }

                } else {
                    Toast.makeText(AdotadoActivity.this, "Dados do cão não encontrado!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdotadoActivity.this, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniciarComponentes() {
        nomeCaoFinal = findViewById(R.id.nomeCaoFinal);
        responsavel = findViewById(R.id.responsavel);
        contatoFinal = findViewById(R.id.contatoFinal);
    }
}