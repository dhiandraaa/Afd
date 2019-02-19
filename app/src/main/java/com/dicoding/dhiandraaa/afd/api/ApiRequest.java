package com.dicoding.dhiandraaa.afd.api;

import com.dicoding.dhiandraaa.afd.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendAfdeling(@Field("KdAfd") String kdAfd,
                                     @Field("Alias") String Alias,
                                     @Field("KdUnit") String kdUnit);
}
