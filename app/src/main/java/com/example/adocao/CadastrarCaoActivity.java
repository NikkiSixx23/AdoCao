package com.example.adocao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CadastrarCaoActivity extends AppCompatActivity {

    Button btCadastrarCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_cao);

        btCadastrarCao = findViewById(R.id.btCadastrarCao);
        btCadastrarCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdocaoActivity.class);
                startActivity(intent);
            }
        });


    }
}