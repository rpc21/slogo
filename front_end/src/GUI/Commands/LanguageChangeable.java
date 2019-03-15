package GUI.Commands;

public interface LanguageChangeable {

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    void setLanguage(Language newLanguage);
}
