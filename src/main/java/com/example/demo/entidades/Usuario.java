package com.example.demo.entidades;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tdoc;     // tipo de documento (ej: CC, TI)

    @Column(nullable = false, unique = true)
    private String numdoc;   // número de documento (login)

    private String papellido; // primer apellido
    private String sapellido; // segundo apellido

    private String pnomb;    // primer nombre
    private String snomb;    // segundo nombre

    private String correo;
    private String password;
    private String telefono;

    @Column(nullable = false)
    private String rol;      // ADMIN | COORDINADOR | ALUMNO

    @Column(unique = true)
    private String registro; // número de registro (solo alumnos)

    public Usuario() {
    }

    // Constructor básico (sin id)
    public Usuario(String tdoc, String numdoc, String papellido, String sapellido,
                   String pnomb, String snomb, String correo, String password,
                   String telefono, String rol, String registro) {
        this.tdoc = tdoc;
        this.numdoc = numdoc;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.pnomb = pnomb;
        this.snomb = snomb;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
        this.registro = registro;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) { 
        this.id = id;
    }

    public String getTdoc() {
        return tdoc;
    }

    public void setTdoc(String tdoc) {
        this.tdoc = tdoc;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public String getPnomb() {
        return pnomb;
    }

    public void setPnomb(String pnomb) {
        this.pnomb = pnomb;
    }

    public String getSnomb() {
        return snomb;
    }

    public void setSnomb(String snomb) {
        this.snomb = snomb;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", numdoc='" + numdoc + '\'' +
                ", pnomb='" + pnomb + '\'' +
                ", papellido='" + papellido + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}

