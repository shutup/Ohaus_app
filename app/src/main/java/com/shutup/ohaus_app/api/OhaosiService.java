package com.shutup.ohaus_app.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by shutup on 16/9/22.
 */

public interface OhaosiService {
    @GET("api/category/all")
    Call<List<CategoryEntity>> getAllCategory();


    @FormUrlEncoded
    @POST("api/users/login")
    Call<ResponseBody> userLogin(@Field("phone") String phone, @Field("password") String password);


    @GET("api/products/list")
    Call<CategoryListEnitty> getProductionLists(@Query("cid") String cid, @Query("scid") String scid);
}
