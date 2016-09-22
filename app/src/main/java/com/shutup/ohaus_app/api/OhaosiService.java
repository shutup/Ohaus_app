package com.shutup.ohaus_app.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shutup on 16/9/22.
 */

public interface OhaosiService {
    @GET("api/category/all")
    Call<List<CategoryEntity>> getAllCategory();
}
