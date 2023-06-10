package com.example.imagineria_web_android.Repository;

public class ObraRepository {

  /*  private ObraApi api;

    public ObraRepository() {
        api = RetrofitInstance.getRetrofitInstance().create(ObraApi.class);
    }

    public LiveData<List<Obra>> getObras(String token) {
        MutableLiveData<List<Obra>> obrasData = new MutableLiveData<>();
        api.getObras("Bearer " + token).enqueue(new Callback<ObrasResponse>() {
            @Override
            public void onResponse(Call<ObrasResponse> call, Response<ObrasResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    obrasData.setValue(response.body().getContent()); // Aquí es donde obtienes las obras de la respuesta
                }
            }

            @Override
            public void onFailure(Call<ObrasResponse> call, Throwable t) {
                obrasData.setValue(null);
            }
        });
        return obrasData;
    }*/
}
