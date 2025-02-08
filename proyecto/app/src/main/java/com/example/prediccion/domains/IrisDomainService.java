package com.example.prediccion.domains;


import com.example.prediccion.entity.Iris;

public interface IrisDomainService {
    public String filtrar(String prediccion);
    public String predict(Iris iris);
}