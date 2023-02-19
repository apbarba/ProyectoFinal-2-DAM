package com.salesianostriana.dam.imagineria_web.search.spec.FavoritosSearch;

import com.salesianostriana.dam.imagineria_web.model.Favoritos;
import com.salesianostriana.dam.imagineria_web.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;

import java.util.List;

public class FavoritosSpecificationBuilder extends GenericSpecificationBuilder<Favoritos> {

    public FavoritosSpecificationBuilder(List<SearchCriteria> params) {

        super(params, Favoritos.class);
    }
}
