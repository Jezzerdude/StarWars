package jeremy.code.starwars.View.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import jeremy.code.starwars.API.Model.CharactersList;
import jeremy.code.starwars.R;
import jeremy.code.starwars.View.Listener.RecyclerItemClickListener;
import jeremy.code.starwars.View.Adapter.RecyclerViewCharacterAdapter;
import jeremy.code.starwars.Util.Util;
import jeremy.code.starwars.ViewModel.CharacterListViewModel;

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

        mRecyclerView.setAdapter(new RecyclerViewCharacterAdapter(new CharactersList()));

        refreshData();

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent myIntent = new Intent(MainActivity.this, CharacterActivity.class);
                myIntent.putExtra(Util.RESULT, mCharactersList.getResults().get(position));
                MainActivity.this.startActivity(myIntent);
            }
        }));
    }


    private void observeViewModel(final CharacterListViewModel viewModel) {
        viewModel.getCharacterListLiveData().observe(this, new Observer<CharactersList>() {
            @Override
            public void onChanged(@Nullable CharactersList charactersList) {
                if (charactersList != null) {
                    setCharacterAdapter(charactersList);
                    hideProgressBar();
                } else {
                    Snackbar errorMessageSnack = Snackbar.
                            make(findViewById(R.id.main_constrain_layout), R.string.error_message, Snackbar.LENGTH_INDEFINITE);
                    errorMessageSnack.setAction(R.string.refresh_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            refreshData();
                        }
                    });
                    errorMessageSnack.show();
                    hideProgressBar();
                }
            }
        });
    }

    private void setCharacterAdapter(@NonNull CharactersList charactersList) {
        this.mCharactersList = charactersList;
        RecyclerView.Adapter adapter = new RecyclerViewCharacterAdapter(mCharactersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void refreshData() {
        showProgressBar();
        CharacterListViewModel viewModel = ViewModelProviders.of(this).get(CharacterListViewModel.class);
        observeViewModel(viewModel);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
