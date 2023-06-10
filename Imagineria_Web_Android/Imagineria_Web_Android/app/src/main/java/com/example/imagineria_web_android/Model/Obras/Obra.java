package com.example.imagineria_web_android.Model.Obras;

public class Obra {

    private String id;
    private String name;
    private String estilo;
    private String fecha;
    private double precio;
    private String estado;
    private String titulo;
    private String img;
    private String categoria;

    public Obra() {
        //PARA EL POST
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Obra(String id, String name,String estilo, String fecha, double precio, String estado, String titulo, String img, String categoria) {
        this.id = id;
        this.name = name;
        this.estilo = estilo;
        this.fecha = fecha;
        this.precio = precio;
        this.estado = estado;
        this.titulo = titulo;
        this.img = img;
        this.categoria = categoria;
    }
}
