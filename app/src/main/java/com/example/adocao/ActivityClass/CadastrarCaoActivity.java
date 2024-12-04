package com.example.adocao.ActivityClass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adocao.Model.CaoModel;
import com.example.adocao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class CadastrarCaoActivity extends AppCompatActivity {

    private ImageView imageCaoRegister;
    private EditText nomeCaoRegister;
    private EditText corCaoRegister;
    private EditText porteCaoRegister;
    private EditText sexoCaoRegister;
    private EditText racaCaoRegister;
    private EditText historicoCaoRigister;
    private Button btCadastrarCao;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_cao);

        mAuth = FirebaseAuth.getInstance();

        iniciarComponentes();
        ImageButton btGaleriaCao = findViewById(R.id.btGaleriaCao);
        btGaleriaCao.setOnClickListener(v -> openGallery());

        racaCaoRegister.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        btCadastrarCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CaoModel caoModel = new CaoModel();

                caoModel.setNomeDoCao(nomeCaoRegister.getText().toString());
                caoModel.setCor(corCaoRegister.getText().toString());
                caoModel.setPorte(porteCaoRegister.getText().toString());
                caoModel.setSexo(sexoCaoRegister.getText().toString());
                caoModel.setRaca(racaCaoRegister.getText().toString());
                caoModel.setHistorico(historicoCaoRigister.getText().toString());

                caoModel.salvar();
                cadastrarCao();
            }
        });
    }

    private void iniciarComponentes(){
        imageCaoRegister = findViewById(R.id.imageCaoRegister);
        nomeCaoRegister = findViewById(R.id.nomeCaoRegister);
        corCaoRegister = findViewById(R.id.corCaoRegister);
        porteCaoRegister = findViewById(R.id.porteCaoRegister);
        sexoCaoRegister = findViewById(R.id.sexoCaoRegister);
        racaCaoRegister = findViewById(R.id.racaCaoRegister);
        historicoCaoRigister = findViewById(R.id.historicoCaoRegister);
        btCadastrarCao = findViewById(R.id.btCadastrarCao);
    }

    private void cadastrarCao() {
        Intent intent = new Intent(CadastrarCaoActivity.this, AdocaoActivity.class);
        startActivity(intent);
        finish();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Uri selectedImageUri = result.getData().getData();
            if (selectedImageUri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    imageCaoRegister.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Erro ao carregar a imagem!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });


}