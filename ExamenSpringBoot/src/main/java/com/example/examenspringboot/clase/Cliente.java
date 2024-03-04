package com.example.examenspringboot.clase;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente {
    @Id
    private Long id;
    private String nombre;
    private Long total;
    private String estado;

    public Cliente(String nombre, Long totalVentas, String estado) {
        this.nombre = nombre;
        this.total = totalVentas;
        this.estado = estado;
    }
    public Cliente(Long id,String nombre, Long totalVentas, String estado) {
        this.id=id;
        this.nombre = nombre;
        this.total = totalVentas;
        this.estado = estado;
    }
    public Cliente(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long totalVentas) {
        this.total = totalVentas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", totalVentas=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}
