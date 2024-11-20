package com.example.adocao.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adocao.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilActivity extends AppCompatActivity {

    private ImageButton btSair;
    private Button btAUmigo;
    private TextView nomeUsuario;
    private TextView emailUsuario;
    private TextView cidadeUsuario;
    private TextView UFUsuario;
    private TextView contatoUsuario;
    FirebaseFirestore bdAdocao = FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        IniciarComponentes();

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

    @Override
    protected void onStart() {
        super.onStart();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        
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