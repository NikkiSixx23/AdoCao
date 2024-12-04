package com.example.adocao.ActivityClass;

import android.annotation.SuppressLint;
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

import com.example.adocao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button btEntrar;
    private Button btCadastro;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        btEntrar = findViewById(R.id.btEntrar);
        btCadastro = findViewById(R.id.btCadastro);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = email.getText().toString();
                String loginSenha = senha.getText().toString();

                if (!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginSenha)) {
                    mAuth.signInWithEmailAndPassword(loginEmail, loginSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                abrirTelaPrincipal();
                            } else {
                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastrarUsuarioActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void abrirTelaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, AdocaoActivity.class);
        startActivity(intent);
        finish();
    }
}