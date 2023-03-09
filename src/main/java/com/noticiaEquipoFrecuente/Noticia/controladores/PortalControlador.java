package com.noticiaEquipoFrecuente.Noticia.controladores;

import com.noticiaEquipoFrecuente.Noticia.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String index(ModelMap modelo, @ModelAttribute("exito") String exito) {
        modelo.put("exito", exito);
        modelo.put("noticias", noticiaServicio.ListarNoticias());
        return "index.html";
    }
}
