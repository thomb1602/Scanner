package com.example.thomb.scanner.Helpers;

import java.util.regex.Pattern;

/**
 * Created by spam_ on 16/08/2018.
 */

public class HelperMethods {

    public boolean isGuid(String scanContents)
    {
        String guidRegex = "[0-9a-f]{8}[-][0-9a-f]{4}[-][0-9a-f]{4}[-][0-9a-f]{4}[-][0-9a-f]{12}";
        return Pattern.matches(guidRegex, scanContents);
    }
}
