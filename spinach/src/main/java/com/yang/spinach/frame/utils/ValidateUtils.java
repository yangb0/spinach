package com.yang.spinach.frame.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim())) return s.matches("^[0-9]*$");
        else return false;
    }
}
