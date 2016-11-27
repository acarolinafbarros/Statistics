package org.iStat.api.iUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.util.ObjectUtils;

public class IConvertUtil {

    /**
     * This methods converts a String input with float values (#.#) divided with
     * ; to a list of Float.
     * 
     * Returns:
     * - Null, if input is empty.
     * - Exception, if input is bad formatted.
     * - List<Float>, if everything is ok.
     * 
     * @param input
     *            - String
     * @return List<Float> or null or Exception
     */
    public static List<Float> convertStringIntoListFloat(String input) {
        List<Float> output = null;
        if (!ObjectUtils.isEmpty(input)) {
            StringTokenizer strTokenizer = new StringTokenizer(input, ";");
            if (strTokenizer.countTokens() > 0) {
                output = new ArrayList<Float>();
                while (strTokenizer.hasMoreTokens()) {
                    output.add(Float.valueOf(strTokenizer.nextToken()));
                }
            }
        }
        return output;
    }

}
