package com.applex.icd__to__excel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ICDCodeModel implements Serializable {

    public String code;
    public Title title;

    public static class Title implements Serializable {
        @SerializedName("@language")
        public String language;

        @SerializedName("@value")
        public String value;
    }
}
