package com.alura.literalura.repository;

import org.springframework.stereotype.Repository;


public interface IConvierteDatos {

    <T> T obtenerDatos (String Json, Class<T> clase);
}
