package GUI.Commands;

import java.util.ResourceBundle;

/**
 * The Language enum provides all the different languages available for the program.  The Language enum will load the
 * appropriate Resource Bundle (Properties File) for the corresponding language and allows the program to easily get
 * translated words from just the Language rather than requiring each class to have access to all the different
 * properties files
 * Author: Ryan Culhane, Louis Jensen
 */
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

    /**
     * Returns a string of the word translated into the current language
     * @param key word to be translated
     * @return word translated into the current language
     */
    public String getTranslatedWord(String key){
        return myResourceBundle.getString(key);
    }

    /**
     * Return string representation of language
     * @return string representation of language
     */
    public String getLanguageString(){
        return languageString;
    }
}
