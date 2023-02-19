package com.salesianostriana.dam.imagineria_web.search.spec.ObrasSearch;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;

import java.util.List;

public class ObrasSpecificationBuilder extends GenericSpecificationBuilder<Obras> {

    public ObrasSpecificationBuilder(List<SearchCriteria> params){

        super(params, Obras.class);
    }
}
