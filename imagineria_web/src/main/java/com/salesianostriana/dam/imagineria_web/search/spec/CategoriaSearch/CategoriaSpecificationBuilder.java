package com.salesianostriana.dam.imagineria_web.search.spec.CategoriaSearch;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;

import java.util.List;

public class CategoriaSpecificationBuilder extends GenericSpecificationBuilder<Categoria> {

    public CategoriaSpecificationBuilder(List<SearchCriteria> params) {

        super(params, Categoria.class);
    }
}
