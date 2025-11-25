package com.example.demo.entidades;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "examenes")
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Si el examen fue anulado, puntajetotal puede ser null y anulado = true
    private Integer puntajetotal;
    private Boolean anulado = false;

    // Materias — nombres de una sola palabra
    private Integer comunicacion;   // comunicación escrita
    private Integer razonamiento;   // razonamiento cuantitativo
    private Integer lectura;        // lectura crítica
    private Integer ciudadana;      // competencias ciudadanas
    private Integer ingles;         // inglés
    private Integer proyectos;      // formulación de proyectos de ingeniería
    private Integer pcientifico;    // pensamiento científico - matemáticas y estadística
    private Integer dsoft;          // diseño de software

    // escala de ingles (A0..B2) — solo campo textual
    private String nivelingles;

    public Examen() {
    }

    // Constructor básico (sin id)
    public Examen(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) { 
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntajetotal() {
        return puntajetotal;
    }

    public void setPuntajetotal(Integer puntajetotal) {
        this.puntajetotal = puntajetotal;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Integer getComunicacion() {
        return comunicacion;
    }

    public void setComunicacion(Integer comunicacion) {
        this.comunicacion = comunicacion;
    }

    public Integer getRazonamiento() {
        return razonamiento;
    }

    public void setRazonamiento(Integer razonamiento) {
        this.razonamiento = razonamiento;
    }

    public Integer getLectura() {
        return lectura;
    }

    public void setLectura(Integer lectura) {
        this.lectura = lectura;
    }

    public Integer getCiudadana() {
        return ciudadana;
    }

    public void setCiudadana(Integer ciudadana) {
        this.ciudadana = ciudadana;
    }

    public Integer getIngles() {
        return ingles;
    }

    public void setIngles(Integer ingles) {
        this.ingles = ingles;
    }

    public Integer getProyectos() {
        return proyectos;
    }

    public void setProyectos(Integer proyectos) {
        this.proyectos = proyectos;
    }

    public Integer getPcientifico() {
        return pcientifico;
    }

    public void setPcientifico(Integer pcientifico) {
        this.pcientifico = pcientifico;
    }

    public Integer getDsoft() {
        return dsoft;
    }

    public void setDsoft(Integer dsoft) {
        this.dsoft = dsoft;
    }

    public String getNivelingles() {
        return nivelingles;
    }

    public void setNivelingles(String nivelingles) {
        this.nivelingles = nivelingles;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getNumdoc() : "null") +
                ", puntajetotal=" + puntajetotal +
                ", anulado=" + anulado +
                '}';
    }
}
