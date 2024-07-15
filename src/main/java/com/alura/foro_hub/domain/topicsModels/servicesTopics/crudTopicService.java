package com.alura.foro_hub.domain.topicsModels.servicesTopics;

import com.alura.foro_hub.domain.repositories.topicRepository;
import com.alura.foro_hub.domain.repositories.userRepository;
import com.alura.foro_hub.domain.topicsModels.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class crudTopicService {
    @Autowired
    private topicRepository topicRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    List<ValidadorDeTopicos> validadores;
    /*
    @Autowired
    List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento;
     */

    public DatosDetalleTopico crear(DatosCrearTopico datos){
        if(datos.idUsuario()!=null&&!userRepository.existsById(datos.idUsuario())){
            throw new ValidacionDeIntegridad("Id de usuario no encontrado");
        }
        validadores.forEach(v->v.validar(datos));
        var usuario = userRepository.findById(datos.idUsuario()).get();
        var topico = new Topic(
                datos.titulo(),
                datos.mensaje(),
                datos.status(),
                usuario,
                datos.nombreCurso()
        );
        topicRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }


}