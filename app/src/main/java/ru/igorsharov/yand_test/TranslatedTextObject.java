package ru.igorsharov.yand_test;

import java.util.ArrayList;

public class TranslatedTextObject {

    private String requestedText;
    private String translatedText;
    private Boolean isFavourite;
    public static ArrayList<TranslatedTextObject> translate = new ArrayList<>();
    public static ArrayList<TranslatedTextObject> translateFavourite = new ArrayList<>();

    public String getRequestedText() {
        return requestedText;
    }

    public void setRequestedText(String requestedText) {
        this.requestedText = requestedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public Boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}
