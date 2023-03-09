package com.noticiaEquipoFrecuente.Noticia.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {
    @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2") private String idNoticia; 
    private String titulo;
    @JoinColumn(columnDefinition = "VARCHAR(10000)") private String cuerpo;

    public Noticia() {
    }

    public Noticia(String idNoticia, String titulo, String cuerpo) {
        this.idNoticia = idNoticia;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Override
    public String toString() {
        return "Noticia{" + "idNoticia=" + idNoticia + ", titulo=" + titulo + ", cuerpo=" + cuerpo + '}';
    }
}
