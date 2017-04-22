package ru.igorsharov.yand_test;

import java.util.ArrayList;

public class TranslatedText {

    private String requestedText;
    private String translatedText;
    private Boolean isFavourite;
    private static ArrayList<TranslatedText> exchangeTranslate;

    public static ArrayList<TranslatedText> getExchangeTranslate() {
        return exchangeTranslate;
    }

    public static void setExchangeTranslate(ArrayList<TranslatedText> exchangeTranslate) {
        TranslatedText.exchangeTranslate = exchangeTranslate;
    }


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
