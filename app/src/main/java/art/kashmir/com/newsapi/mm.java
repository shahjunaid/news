package art.kashmir.com.newsapi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 08-02-2018.
 */

public class mm implements Parcelable{
    private String heading,description,imgurl,url;

    public mm(String heading, String description, String imgurl, String url) {
        this.heading = heading;
        this.description = description;
        this.imgurl = imgurl;
        this.url = url;
    }

    protected mm(Parcel in) {
        heading = in.readString();
        description = in.readString();
        imgurl = in.readString();
        url = in.readString();
    }

    public static final Creator<mm> CREATOR = new Creator<mm>() {
        @Override
        public mm createFromParcel(Parcel in) {
            return new mm(in);
        }

        @Override
        public mm[] newArray(int size) {
            return new mm[size];
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

    public String getUrl() {
        return url;
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
        dest.writeString(url);
    }
}
