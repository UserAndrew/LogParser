package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andreibudin on 12.07.2018.
 */
public class MyRegExp {

        public static String methodNameID = "\\w*:\\d{5}";
        public static String time = "[2][0][1][0-5]-[0-1]\\d-[0-3]\\d[T]([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d,\\d{3}";
        //public static String date = "[2][0][1][0-5]-[0-1]\\d-[0-3]\\d";
        //public static String time = "([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d,\\d{3}";
        static String s, s1;

        public static Pattern p_methodName = Pattern.compile(methodNameID);
        public static Pattern p_time = Pattern.compile(time);

        public static String getMethodNameID(String str) {
            Matcher mNameID = p_methodName.matcher(str);
            if(mNameID.find()) s = mNameID.group();
            return s;
        }

        public static String getTimes(String str1) {
            Matcher mTime = p_time.matcher(str1);
            if(mTime.find()) s1 = mTime.group();
            return s1;
        }


}
