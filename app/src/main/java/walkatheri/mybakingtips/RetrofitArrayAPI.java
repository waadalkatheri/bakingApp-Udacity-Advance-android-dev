package blueappsoftware.mybakingtips;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jaink on 22-08-2017.
 */

public interface RetrofitArrayAPI {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipeDetails();

}
