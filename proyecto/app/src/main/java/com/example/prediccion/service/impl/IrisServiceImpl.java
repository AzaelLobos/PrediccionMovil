package com.example.prediccion.service.impl;

import com.example.prediccion.entity.Iris;
import com.example.prediccion.service.IrisService;
import com.example.prediccion.domains.IrisDomainService;

public class IrisServiceImpl implements IrisService {
    private IrisDomainService irisDomainService;

    public IrisServiceImpl(IrisDomainService irisDomainService) {
        this.irisDomainService = irisDomainService;
    }

    @Override
    public String prediccion(Iris iris) {
        String rawPrediction = irisDomainService.predict(iris);
        return irisDomainService.filtrar(rawPrediction);
    }
}