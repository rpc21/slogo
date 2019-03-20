package GUI.Commands;

/**
 * LanguageChangeable is an interface that indicates a class has a language dependency.  The setLanguage method
 * allows the program to update the language dependent features of that class when the language of the program is
 * changed.  The LanguageChangeable interface also allows language-dependent gui components to be grouped together in
 * a collection so the language can easily be changed across the entire program.
 * Author: Ryan Culhane
 */
public interface LanguageChangeable {

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    void setLanguage(Language newLanguage);
}
