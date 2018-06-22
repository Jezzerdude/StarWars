package jeremy.code.starwars.API.Model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharactersList implements Parcelable {

    @SerializedName("count")
    @Expose
    private final Integer count;
    @SerializedName("next")
    @Expose
    private final String next;
    @SerializedName("previous")
    @Expose
    private final Object previous;
    @SerializedName("results")
    @Expose
    private final List<Result> results = new ArrayList<>();
    public final static Parcelable.Creator<CharactersList> CREATOR = new Creator<CharactersList>() {

        @SuppressWarnings({
                "unchecked"
        })
        public CharactersList createFromParcel(Parcel in) {
            return new CharactersList(in);
        }

        public CharactersList[] newArray(int size) {
            return (new CharactersList[size]);
        }

    };

    public CharactersList() {
        this.count = 0;
        this.next = null;
        this.previous = null;
    }

    private CharactersList(Parcel in) {
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.next = ((String) in.readValue((String.class.getClassLoader())));
        this.previous = in.readValue((Object.class.getClassLoader()));
        in.readList(this.results, (Result.class.getClassLoader()));
    }

    public List<Result> getResults() {
        return results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(next);
        dest.writeValue(previous);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
