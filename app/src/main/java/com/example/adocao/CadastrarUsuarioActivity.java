package com.example.adocao;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private EditText emailRegister;
    private EditText senhaRegister;
    private Button btCadastrarUsuario;
    private ProgressBar loginProgressBarRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_usuario);

        mAuth = FirebaseAuth.getInstance();

        emailRegister = findViewById(R.id.email);
        senhaRegister = findViewById(R.id.senha);
        btCadastrarUsuario = findViewById(R.id.btCadastrarUsuario);

        btCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registrarEmail = emailRegister.getText().toString();
                String registrarSenha = senhaRegister.getText().toString();

                if(!TextUtils.isEmpty(registrarEmail) || TextUtils.isEmpty(registrarSenha)){
                    mAuth.createUserWithEmailAndPassword(registrarEmail, registrarSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                abrirTelaPrincipal();
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(CadastrarUsuarioActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(CadastrarUsuarioActivity.this, AdocaoActivity.class);
        startActivity(intent);
        finish();
    }
}