package com.applex.icd__to__excel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ResponseModel implements Serializable {

    public List<String> child;
    public String code;
    public Title title;

    public static class Title implements Serializable {
        @SerializedName("@language")
        public String language;

        @SerializedName("@value")
        public String value;
    }
}
