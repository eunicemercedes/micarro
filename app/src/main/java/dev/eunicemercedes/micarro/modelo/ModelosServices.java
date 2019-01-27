package dev.eunicemercedes.micarro.modelo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelosServices {


    @GET("1KiNFsJheVFPcpcn5BygX2ShaOqGSna-T?e=download")
    public Call<Modelos> getModels();

    @GET("1KiNFsJheVFPcpcn5BygX2ShaOqGSna-T?e=download")
    public Call<List<Modelo>> getModelo();

}
