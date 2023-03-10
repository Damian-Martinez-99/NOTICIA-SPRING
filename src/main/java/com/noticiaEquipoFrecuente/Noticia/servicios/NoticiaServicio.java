package com.noticiaEquipoFrecuente.Noticia.servicios;

import com.noticiaEquipoFrecuente.Noticia.entidades.Noticia;
import com.noticiaEquipoFrecuente.Noticia.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio notiRepo;

    @Transactional
    public void CrearNoticia(String titulo, String cuerpo) throws Exception {
        Validar(titulo, cuerpo);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        notiRepo.save(noticia);
    }

    @Transactional
    public void ModificarNoticia(String titulo, String cuerpo, String idNoticia) throws Exception {
        Validar(titulo, cuerpo);
        ValidarID(idNoticia);

        Optional<Noticia> respuesta = notiRepo.findById(idNoticia);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);

            notiRepo.save(noticia);
        }
    }

    public List<Noticia> ListarNoticias() {
        List<Noticia> noticias = new ArrayList();

        noticias = notiRepo.findAll();

        return noticias;
    }

    private void Validar(String titulo, String cuerpo) throws Exception {
        if (titulo.isEmpty() || titulo == null || titulo.equalsIgnoreCase(" ")) {
            throw new Exception("El titulo no puede ser nulo o estar vacio.");
        }
        if (cuerpo.isEmpty() || cuerpo == null || cuerpo.equalsIgnoreCase(" ")) {
            throw new Exception("El cuerpo no puede ser nulo o estar vacio.");
        }
    }

    @Transactional
    public void EliminarNoticia(String idNoticia) throws Exception {
        ValidarID(idNoticia);

        Optional<Noticia> respuesta = notiRepo.findById(idNoticia);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            notiRepo.delete(noticia);
        }
    }

    private void ValidarID(String idNoticia) throws Exception {
        if (idNoticia.isEmpty() || idNoticia == null || idNoticia .equalsIgnoreCase(" ")) {
            throw new Exception("El ID de la noticia no puede ser nulo o estar vacio.");
        }
    }
    
    public Noticia BuscarPorID(String idNoticia) {
        return notiRepo.findById(idNoticia).get();
    }
}
