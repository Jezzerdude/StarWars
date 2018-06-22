package jeremy.code.starwars.API.Repository;

import jeremy.code.starwars.API.Model.CharactersList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SWApi {

    @GET("people/")
    Call<CharactersList> getCharacterList();

}
