package com.salesianostriana.dam.imagineria_web.search.spec.ImagineroSearch;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;

import java.util.List;

public class ImagineroSpecificationBuilder extends GenericSpecificationBuilder<Imaginero> {

    public ImagineroSpecificationBuilder(List<SearchCriteria> params) {

        super(params, Imaginero.class);
    }
}
