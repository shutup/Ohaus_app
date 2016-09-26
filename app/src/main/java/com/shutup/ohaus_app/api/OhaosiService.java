package com.shutup.ohaus_app.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by shutup on 16/9/22.
 */

public interface OhaosiService {
    @GET("api/category/all")
    Call<List<CategoryEntity>> getAllCategory();


    @FormUrlEncoded
    @POST("api/users/login")
    Call<ResponseBody> userLogin(@Field("phone") String phone, @Field("password") String password);
}
