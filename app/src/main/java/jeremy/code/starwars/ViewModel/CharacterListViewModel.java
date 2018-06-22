package jeremy.code.starwars.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import jeremy.code.starwars.API.Model.CharactersList;
import jeremy.code.starwars.API.Repository.SWApiClient;

public class CharacterListViewModel extends AndroidViewModel{

    private final MutableLiveData<CharactersList> characterListLiveData;

    public CharacterListViewModel(@NonNull Application application) {
        super(application);
        characterListLiveData = SWApiClient.getInstance().getCharacterList();
    }

    public LiveData<CharactersList> getCharacterListLiveData() {
        return characterListLiveData;
    }
}