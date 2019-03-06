package GUI;

import java.util.ResourceBundle;

public enum Language {

    CHINESE("Chinese"),
    ENGLISH("English"),
    FRENCH("French"),
    GERMAN("German"),
    ITALIAN("Italian"),
    PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"),
    SPANISH("Spanish"),
    URDU("Urdu");

    private ResourceBundle myResourceBundle;
    private String languageString;

    Language(String language){
        languageString = language;
        myResourceBundle = ResourceBundle.getBundle(language);
    }

    public String getTranslatedWord(String key){
        return myResourceBundle.getString(key);
    }

    public String getLanguageString(){
        return languageString;
    }
}
