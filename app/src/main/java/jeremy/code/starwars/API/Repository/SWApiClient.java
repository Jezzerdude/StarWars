package jeremy.code.starwars.API.Repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import jeremy.code.starwars.API.Model.CharactersList;
import jeremy.code.starwars.Util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SWApiClient {

    private final SWApi swApi;
    private static SWApiClient swApiClient;

    private SWApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        swApi = retrofit.create(SWApi.class);
    }

    public synchronized static SWApiClient getInstance() {
        if (swApiClient == null) {
            swApiClient = new SWApiClient();
        }
        return swApiClient;
    }

    public MutableLiveData<CharactersList> getCharacterList() {
        final MutableLiveData<CharactersList> data = new MutableLiveData<>();

        swApi.getCharacterList().clone().enqueue(new Callback<CharactersList>() {
            @Override
            public void onResponse(@NonNull Call<CharactersList> call, @NonNull Response<CharactersList> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CharactersList> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
