package art.kashmir.com.newsapi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 01-02-2018.
 */

public class model implements Parcelable{
    private String heading,description,imgurl;

    public model(String heading, String description, String imgurl) {
        this.heading = heading;
        this.description = description;
        this.imgurl = imgurl;
    }

    protected model(Parcel in) {
        heading = in.readString();
        description = in.readString();
        imgurl = in.readString();
    }

    public static final Creator<model> CREATOR = new Creator<model>() {
        @Override
        public model createFromParcel(Parcel in) {
            return new model(in);
        }

        @Override
        public model[] newArray(int size) {
            return new model[size];
        }
    };

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getImgurl() {
        return imgurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(heading);
        dest.writeString(description);
        dest.writeString(imgurl);
    }
}
