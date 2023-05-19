package com.example.imagineria_web_android.Model.Obras;

public class Obra {

    private String id;
    private String nombre;
    private String estilo;
    private String fecha;
    private double precio;
    private String estado;
    private String img;

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

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Obra(String id, String nombre, String estilo, String fecha, double precio, String estado, String img) {
        this.id = id;
        this.nombre = nombre;
        this.estilo = estilo;
        this.fecha = fecha;
        this.precio = precio;
        this.estado = estado;
        this.img = img;
    }
}
