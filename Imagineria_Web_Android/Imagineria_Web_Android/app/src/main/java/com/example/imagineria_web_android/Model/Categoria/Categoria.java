package com.example.imagineria_web_android.Model.Categoria;

import com.example.imagineria_web_android.Model.Obras.Obra;

import java.util.List;

public class Categoria {

    private String id;
    private String nombre;
    private String descripcion;
    private List<Obra> obras;

    public Categoria(String id, String nombre, String descripcion, List<Obra> obras) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obras = obras;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
}
