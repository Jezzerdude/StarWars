package jeremy.code.starwars.API.Repository;

import android.support.annotation.NonNull;

import jeremy.code.starwars.Util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SWApiClient {

    @NonNull
    public static Retrofit getClient() {

        return new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
