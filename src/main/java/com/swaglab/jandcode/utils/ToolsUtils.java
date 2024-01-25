package com.swaglab.jandcode.utils;

public class ToolsUtils {

    /**
     * This method going to put any word the first Letter in capital letter
     *
     * @param word element string that method will change
     * @return String first letter in capital letter
     */
    public static String capitalizeWord (String word){
        if(word == null || word.isEmpty()){
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
