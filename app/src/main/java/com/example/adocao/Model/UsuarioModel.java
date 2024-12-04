package com.example.adocao.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioModel {
    private String nome;
    private String email;
    private String cidade;
    private String UF;
    private String contato;


    public UsuarioModel(){
    }

    public UsuarioModel(String nome, String email, String cidade, String UF, String contato) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.UF = UF;
        this.contato = contato;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }



    public void salvar(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usuarios");
        reference.child(userId).setValue(this);
    }
}








