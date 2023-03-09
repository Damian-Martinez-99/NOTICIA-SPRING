package com.noticiaEquipoFrecuente.Noticia.controladores;

import com.noticiaEquipoFrecuente.Noticia.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/{idNoticia}")
    public String idNoticia(@PathVariable String idNoticia, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.BuscarPorID(idNoticia));
        modelo.put("noticias", noticiaServicio.ListarNoticias());
        return "noticia.html";
    }

    @GetMapping("/creacionNoticia")
    public String creacionNoticia(ModelMap modelo) {
        modelo.put("noticias", noticiaServicio.ListarNoticias());
        return "noticia_form.html";
    }

    @PostMapping("/ingresoNoticia")
    public String ingresoNoticia(@RequestParam(required = false) String titulo, @RequestParam(required = false) String cuerpo, ModelMap modelo, RedirectAttributes redireccion) {
        try {
            noticiaServicio.CrearNoticia(titulo, cuerpo);
            redireccion.addFlashAttribute("exito", "La noticia fue cargada con exito");
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        return "redirect:/";
    }

    @GetMapping("/modificarNoticia/{idNoticia}")
    public String modificarNoticia(@PathVariable String idNoticia, ModelMap modelo, @ModelAttribute("error") String error) {
        modelo.put("error", error);
        modelo.put("noticia", noticiaServicio.BuscarPorID(idNoticia));
        return "noticia_modificar.html";
    }

    @PostMapping("/modificacionNoticia/{idNoticia}")
    public String modificacionNoticia(@RequestParam(required = false) String titulo, @RequestParam(required = false) String cuerpo, @PathVariable String idNoticia, ModelMap modelo, RedirectAttributes redireccion) {
        try {
            noticiaServicio.ModificarNoticia(titulo, cuerpo, idNoticia);
            redireccion.addFlashAttribute("exito", "La noticia fue modificada con exito");
        } catch (Exception ex) {
            redireccion.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/modificarNoticia/" + idNoticia;
        }
        modelo.put("noticias", noticiaServicio.ListarNoticias());
        return "redirect:/";
    }

//    @GetMapping("/eliminarNoticia/{idNoticia}")
//    public String eliminarNoticia(@PathVariable String idNoticia, ModelMap modelo) {
//        modelo.put("noticia", noticiaServicio.BuscarPorID(idNoticia));
//        return "noticia_eliminar.html";
//    }
    
    @GetMapping("/eliminacionNoticia/{idNoticia}")
    public String eliminacionNoticia(@PathVariable String idNoticia, ModelMap modelo, RedirectAttributes redireccion) {
        modelo.put("noticia", noticiaServicio.BuscarPorID(idNoticia));
        try {
            noticiaServicio.EliminarNoticia(idNoticia);
            redireccion.addFlashAttribute("exito", "La noticia fue eliminada con exito");
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "/{idNoticia}";
        }
        modelo.put("noticias", noticiaServicio.ListarNoticias());
        return "redirect:/";
    }
}
