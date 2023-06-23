package com.rian.rab.API;

import com.rian.rab.Model.ModelBarbershop;
import com.rian.rab.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelBarbershop> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("foto") String foto,
            @Field("deskripsi") String deskripsi,
            @Field("lokasi") String lokasi,
            @Field("koordinat") String koordinat
    );

    @FormUrlEncoded
    @POST("update.php")
    Call <ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("foto") String foto,
            @Field("deskripsi") String deskripsi,
            @Field("lokasi") String lokasi,
            @Field("koordinat") String koordinat
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call <ModelResponse> ardDelete(
            @Field("id") String id
    );

}
