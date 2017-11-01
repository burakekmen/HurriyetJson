package kodluyoruz.com.hurriyetjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Metadata {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
