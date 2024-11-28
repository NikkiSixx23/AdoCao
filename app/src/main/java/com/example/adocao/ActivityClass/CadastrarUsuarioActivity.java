package com.example.adocao.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adocao.Model.MaskTelefone;
import com.example.adocao.Model.UsuarioModel;
import com.example.adocao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private EditText nomeRegister;
    private EditText emailRegister;
    private EditText cidadeRegister;
    private EditText UFRegister;
    private EditText contatoRegister;
    private EditText senhaRegister;
    private Button btCadastrarUsuario;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_usuario);


        mAuth = FirebaseAuth.getInstance();

        nomeRegister = findViewById(R.id.nome);
        emailRegister = findViewById(R.id.email);
        cidadeRegister = findViewById(R.id.cidade);
        UFRegister = findViewById(R.id.UF);
        contatoRegister = findViewById(R.id.contato);
        senhaRegister = findViewById(R.id.senha);
        btCadastrarUsuario = findViewById(R.id.btCadastrarUsuario);

        UFRegister.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        contatoRegister.addTextChangedListener(new MaskTelefone("(##) #####-####"));


        btCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsuarioModel usuarioModel = new UsuarioModel();

                usuarioModel.setNome (nomeRegister.getText().toString());
                usuarioModel.setEmail(emailRegister.getText().toString());
                usuarioModel.setCidade(cidadeRegister.getText().toString());
                usuarioModel.setUF(UFRegister.getText().toString());
                usuarioModel.setContato(contatoRegister.getText().toString());
                String registrarSenha = senhaRegister.getText().toString();

                if(!TextUtils.isEmpty(usuarioModel.getNome()) || !TextUtils.isEmpty(usuarioModel.getEmail()) || !TextUtils.isEmpty(usuarioModel.getCidade()) || !TextUtils.isEmpty(usuarioModel.getUF()) || !TextUtils.isEmpty(usuarioModel.getContato()) || TextUtils.isEmpty(registrarSenha)){
                    mAuth.createUserWithEmailAndPassword(usuarioModel.getEmail(), registrarSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                usuarioModel.salvar();
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
        Intent intent = new Intent(CadastrarUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}