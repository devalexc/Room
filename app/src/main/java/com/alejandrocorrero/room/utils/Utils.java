package com.alejandrocorrero.room.utils;


import android.text.TextUtils;

public class Utils {
    public static boolean isCifNumValid(String cif) {
        if(TextUtils.isEmpty(cif)){
            return false;
        }
       // String[] arraycif = {"A","B","C","D","E","F","G","H","J","N","P","Q","R","S","U","V","W"};
        cif = cif.toUpperCase();
        return cif.length() == 9;
     /*   String Cifnumber = cif.substring(1, cif.length());
        String Cifletter = cif.substring(0, 1);
        try {
            int i = Integer.parseInt(Cifnumber) % 23;
            if (Cifletter.equals(arraycif[i])) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }*/
    }
}
