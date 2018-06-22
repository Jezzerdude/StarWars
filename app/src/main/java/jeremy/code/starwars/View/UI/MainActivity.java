package jeremy.code.starwars.View.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import jeremy.code.starwars.API.Model.CharactersList;
import jeremy.code.starwars.API.Repository.SWApi;
import jeremy.code.starwars.API.Repository.SWApiClient;
import jeremy.code.starwars.R;
import jeremy.code.starwars.Util.Util;
import jeremy.code.starwars.View.Adapter.RecyclerViewCharacterAdapter;
import jeremy.code.starwars.View.Listener.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private CharactersList mCharactersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.refresh_bar);
        mRecyclerView = findViewById(R.id.characters_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        getCharacterList();
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent myIntent = new Intent(MainActivity.this, CharacterActivity.class);
                myIntent.putExtra(Util.RESULT, mCharactersList.getResults().get(position));
                MainActivity.this.startActivity(myIntent);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_refresh){
            mProgressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            getCharacterList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCharacterAdapter(@NonNull CharactersList charactersList) {
        this.mCharactersList = charactersList;
        RecyclerView.Adapter adapter = new RecyclerViewCharacterAdapter(mCharactersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void getCharacterList() {
        final SWApi api = SWApiClient.getClient().create(SWApi.class);
        final Call<CharactersList> getCharactersServiceCall = api.getCharacterList();
        getCharactersServiceCall.clone().enqueue(new Callback<CharactersList>() {

            @Override
            public void onResponse(@NonNull Call<CharactersList> call, @NonNull Response<CharactersList> response) {
                if(response.body() != null) {
                    setCharacterAdapter(response.body());
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(@NonNull Call<CharactersList> call, @NonNull Throwable t) {
                Snackbar errorMessageSnack = Snackbar.
                        make(findViewById(R.id.main_constrain_layout), R.string.error_message, Snackbar.LENGTH_INDEFINITE);
                errorMessageSnack.setAction(R.string.refresh_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getCharacterList();
                    }
                });
                errorMessageSnack.show();hideProgressBar();
            }

        });
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
