package blueappsoftware.mybakingtips;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Model> list;
    private RecyclerViewAdapter adapter;
    private static List<Recipe> mListRecipe;

    String url = "https://d17h27t6h515a5.cloudfront.net/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        /// create a list--
        list = new ArrayList<>();
   /*     list.add( new Model( Model.IMAGE_TYPE, " title 1 ", " subtitle 1" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 2 ", " subtitle 2" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 3 ", " subtitle 3" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 4 ", " subtitle 4" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 5 ", " subtitle 5" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 6 ", " subtitle 6" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 7 ", " subtitle 7" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 8 ", " subtitle 8" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 9 ", " subtitle 9" )  );
        list.add( new Model( Model.IMAGE_TYPE, " title 10 ", " subtitle 10" )  );
*/
        ///  get the list from json file
                getRetrofitArray();

        adapter = new RecyclerViewAdapter( list, MainActivity.this);
            // set adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }

        public void getRetrofitArray(){

            Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

            RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);
            Call<List<Recipe>>  call = service.getRecipeDetails();
            call.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    Log.e("main ", " retrofit response "+ response.body().toString());

                    mListRecipe = response.body();
                    for (int i=0; i<response.body().size();i++){
                        Log.e("main ", " name "+ response.body().get(i).getName() + " serving "+
                           response.body().get(i).getServings());

                        list.add( new Model( Model.IMAGE_TYPE, response.body() ,  response.body().get(i).getName()," Serving " +response.body().get(i).getServings() )  );

                    }
                  adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    Log.e("main ", " retrofit error "+ t.toString());
                }
            });



        }
    public static List<Recipe> getList(){
        return  mListRecipe;
    }

}
