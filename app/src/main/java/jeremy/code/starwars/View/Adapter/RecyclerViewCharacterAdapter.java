package jeremy.code.starwars.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jeremy.code.starwars.API.Model.CharactersList;
import jeremy.code.starwars.R;

public class RecyclerViewCharacterAdapter extends RecyclerView.Adapter<RecyclerViewCharacterAdapter.ViewHolder> {

    private final CharactersList charactersList;

    public RecyclerViewCharacterAdapter(CharactersList charactersList) {
        this.charactersList = charactersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.character_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.characterName.setText(charactersList.getResults().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return charactersList.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView characterName;

        ViewHolder(View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.character_name);
        }
    }
}
