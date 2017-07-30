package guru.springframework.locale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by guillem.puigros on 13/09/2016.
 */
public class LocaleUtil {

    private LocaleUtil() {
        throw new IllegalAccessError("Utility class");
    }


    /**
     * @param acceptLanguage
     * @return
     */
    public static Locale getLocale(String acceptLanguage) {
        if (acceptLanguage == null) {
            return Locale.getDefault();
        }

        HashMap languages = new HashMap();
        ArrayList quality = new ArrayList();
        processAcceptLanguage(acceptLanguage, languages, quality);

        if (languages.size() == 0) {
            return Locale.getDefault();
        }

        ArrayList l = new ArrayList();
        extractLocales(languages, quality, l);

        return (Locale) l.get(0);
    }

    /**
     * @param acceptLanguage
     * @return
     */
    public static Enumeration getLocales(String acceptLanguage) {
        // Short circuit with an empty enumeration if null header
        if (acceptLanguage == null) {
            ArrayList v = new ArrayList();
            v.add(Locale.getDefault());
            return Collections.enumeration(v);
        }

        HashMap languages = new HashMap();
        ArrayList quality = new ArrayList();
        processAcceptLanguage(acceptLanguage, languages, quality);

        if (languages.size() == 0) {
            ArrayList v = new ArrayList();
            v.add(Locale.getDefault());
            return Collections.enumeration(v);
        }
        ArrayList l = new ArrayList();
        extractLocales(languages, quality, l);
        return Collections.enumeration(l);
    }

    private static void processAcceptLanguage(
            String acceptLanguage,
            HashMap languages, ArrayList q) {
        StringTokenizer languageTokenizer =
                new StringTokenizer(acceptLanguage, ",");

        while (languageTokenizer.hasMoreTokens()) {
            String language = languageTokenizer.nextToken().trim();
            int qValueIndex = language.indexOf(';');
            int qIndex = language.indexOf('q');
            int equalIndex = language.indexOf('=');
            Double qValue = new Double(1);

            if (qValueIndex > -1 &&
                    qValueIndex < qIndex &&
                    qIndex < equalIndex) {
                String qValueStr = language.substring(qValueIndex + 1);
                language = language.substring(0, qValueIndex);
                qValueStr = qValueStr.trim().toLowerCase();
                qValueIndex = qValueStr.indexOf('=');
                qValue = new Double(0);
                if (qValueStr.startsWith("q") &&
                        qValueIndex > -1) {
                    qValueStr = qValueStr.substring(qValueIndex + 1);
                    qValue = new Double(qValueStr.trim());
                }
            }

            // XXX
            // may need to handle "*" at some point in time

            if (!"*".equals(language)) {
                String key = qValue.toString();
                ArrayList v;
                if (languages.containsKey(key)) {
                    v = (ArrayList) languages.get(key);
                } else {
                    v = new ArrayList();
                    q.add(qValue);
                }
                v.add(language);
                languages.put(key, v);
            }
        }
    }

    private static void extractLocales(HashMap languages, ArrayList q, ArrayList l) {
        // XXX We will need to order by q value ArrayList in the Future ?
        Enumeration e = Collections.enumeration(q);
        while (e.hasMoreElements()) {
            ArrayList v =
                    (ArrayList) languages.get(((Double) e.nextElement()).toString());
            Enumeration le = Collections.enumeration(v);
            while (le.hasMoreElements()) {
                String language = (String) le.nextElement();
                String country = "";
                int countryIndex = language.indexOf("-");
                if (countryIndex > -1) {
                    country = language.substring(countryIndex + 1).trim();
                    language = language.substring(0, countryIndex).trim();
                }
                l.add(new Locale(language, country));
            }
        }
    }

}
