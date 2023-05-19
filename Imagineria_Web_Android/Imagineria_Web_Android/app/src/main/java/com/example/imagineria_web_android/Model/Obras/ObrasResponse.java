package com.example.imagineria_web_android.Model.Obras;

import com.example.imagineria_web_android.Model.Obras.Obra;

import java.util.List;

public class ObrasResponse {

    private List<Obra> content;

    public List<Obra> getContent() {
        return content;
    }

    public void setContent(List<Obra> content) {
        this.content = content;
    }

    public ObrasResponse(List<Obra> content) {
        this.content = content;
    }
}
