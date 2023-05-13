package com.example.imagineria_web_android.Model.Categoria;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;

import java.util.List;

public class CategoriaResponse {

    public List<Categoria> getContent() {
        return content;
    }

    public void setContent(List<Categoria> content) {
        this.content = content;
    }

    public CategoriaResponse(List<Categoria> content) {
        this.content = content;
    }

    private List<Categoria> content;
}
