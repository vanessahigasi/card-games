package tech.bts.cardgames.util;

import java.util.Collection;

public class Util {

     public static String join(Collection<String> texts, String separator) {

         String result = "";

         for (String text : texts) {
             if (!result.isEmpty()) {
                 result += separator;
             }
             result += text;
         }
         return result;
    }
}
