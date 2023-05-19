package com.example.imagineria_web_android.Model.Imagineros;

import java.util.List;

public class ImagineroResponse {

    public List<Imaginero> getContent() {
        return content;
    }

    public void setContent(List<Imaginero> content) {
        this.content = content;
    }

    public ImagineroResponse(List<Imaginero> content) {
        this.content = content;
    }

    private List<Imaginero> content;
}
