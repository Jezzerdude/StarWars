package jeremy.code.starwars.API.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("mass")
    @Expose
    private String mass;
    @SerializedName("created")
    @Expose
    private String created;

    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    };

    Result(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.height = ((String) in.readValue((String.class.getClassLoader())));
        this.mass = ((String) in.readValue((String.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getCreated() {
        return created;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(height);
        dest.writeValue(mass);
        dest.writeValue(created);
    }

    public int describeContents() {
        return 0;
    }

}
