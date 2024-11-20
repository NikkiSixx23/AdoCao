package com.example.adocao.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CaoModel {
    private String nomeDoCao;
    private String cor;
    private String porte;
    private String sexo;
    private String raca;
    private String historico;

    public CaoModel(){

    }

    public CaoModel(String nomeDoCao, String cor, String porte, String sexo, String raca, String historico) {
        this.nomeDoCao = nomeDoCao;
        this.cor = cor;
        this.porte = porte;
        this.sexo = sexo;
        this.raca = raca;
        this.historico = historico;
    }

    public String getNomeDoCao() {
        return nomeDoCao;
    }

    public void setNomeDoCao(String nomeDoCao) {
        this.nomeDoCao = nomeDoCao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void salvar(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("caes");
        reference.child(userId).setValue(this);
    }
}


