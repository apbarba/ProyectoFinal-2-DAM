package com.example.imagineria_web_android.Model.Imagineros;

import com.example.imagineria_web_android.Model.Obras.Obra;

import java.util.List;

public class Imaginero {


    private String id;
    private String name;
    private int edad;
    private String localidad;
    private List<Obra> obras;

    public Imaginero(String id, String name, int edad, String localidad, List<Obra> obras) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.localidad = localidad;
        this.obras = obras;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
}
