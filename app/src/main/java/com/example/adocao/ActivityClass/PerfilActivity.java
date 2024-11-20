package com.example.adocao.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adocao.Model.UsuarioModel;
import com.example.adocao.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PerfilActivity extends AppCompatActivity {

    private ImageButton btSair;
    private Button btAUmigo;
    private TextView nomeUsuario;
    private TextView emailUsuario;
    private TextView cidadeUsuario;
    private TextView UFUsuario;
    private TextView contatoUsuario;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

        IniciarComponentes();
        carregarDadosUsuario();

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btAUmigo = findViewById(R.id.btAUmigo);
        btAUmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdocaoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void carregarDadosUsuario() {
        String userId = mAuth.getCurrentUser().getUid();

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    UsuarioModel usuarioModel = snapshot.getValue(UsuarioModel.class);

                    if (usuarioModel != null) {
                        nomeUsuario.setText(usuarioModel.getNome());
                        emailUsuario.setText(usuarioModel.getEmail());
                        cidadeUsuario.setText(usuarioModel.getCidade());
                        UFUsuario.setText(usuarioModel.getUF());
                        contatoUsuario.setText(usuarioModel.getContato());
                    }
                } else {
                    Toast.makeText(PerfilActivity.this, "Dados do usuário não encontrados!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PerfilActivity.this, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.nomeUsuario);
        emailUsuario = findViewById(R.id.emailUsuario);
        cidadeUsuario = findViewById(R.id.cidadeUsuario);
        UFUsuario = findViewById(R.id.UFUsuario);
        contatoUsuario = findViewById(R.id.contatoUsuario);
        btSair = findViewById(R.id.btSair);
    }
}