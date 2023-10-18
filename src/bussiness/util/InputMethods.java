package bussiness.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputMethods {
    private static final String ERROR_ALERT = "Invalid format, please re-enter.";
    private static final String FORMAT_ALERT = "Invalid format, please re-enter";
    public static final String EMPTY_ALERT = "Input field cannot be left empty, please re-enter";
    public static final String ERROR_NUMBER = "===>> Vui lòng nhập số nguyên lớn hơn 0";
    public static final String ERROR_SONGID = "===>> Id bài hát phải bắt đầu bằng kí tự S và có đúng 4 kí tự";
    public static final String ERROR_PASSWORD = "Please enter 6 characters";
    public static final String ERROR_CPASSWORD = "Please enter the same password as above";
    /*========================================Input Method Start========================================*/

    /**
     * getString()  Return a String value from the user.
     */
    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.trim().equals("")) {
                System.out.println("\u001B[33m" + EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    /**
     * getEmail()  Return email
     */
    public static String getEmail() {
        boolean isExist = true;
        String result;
        while (true) {
            isExist = true;
            result = getInput();
            if (result.trim().equals("")) {
                System.out.println("\u001B[33m" + EMPTY_ALERT);
                isExist = false;
            } else if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", result)) {
                System.out.println("\u001B[33m" + FORMAT_ALERT);
                isExist = false;
            }
            if (isExist) {
                break;
            }
        }
        return result;
    }


    /**
     * getChar()  Return a Character value from the user.
     */
    public static char getChar() {
        return getString().charAt(0);
    }

    /**
     * getBoolean()  Return a Boolean value from the user.
     */
    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }

    /**
     * getByte()  Return a Byte value from the user.
     */
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.out.println("\u001B[33m" + ERROR_ALERT);
            }
        }
    }

    /**
     * getShort()  Return a Short value from the user.
     */
    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.out.println("\u001B[33m" + ERROR_ALERT);
            }
        }
    }

    /**
     * getInteger()  Return a Integer value from the user.
     */
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.out.println("\u001B[33m" + ERROR_ALERT);
            }
        }
    }

    /**
     * getLong()  Return a Long value from the user.
     */
    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.out.println("\u001B[33m" + ERROR_ALERT);
            }
        }
    }

    /**
     * getFloat()  Return a Float value from the user.
     */
    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.out.println("\u001B[33m" + ERROR_ALERT);
            }
        }
    }

    /**
     * getDouble()  Return a Double value from the user.
     */
    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.out.println("\u001B[33m" + ERROR_ALERT);
            }
        }
    }
    /*========================================Input Method End========================================*/

    /**
     * getInput()  Return any String value from the user.
     */
    public static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * pressAnyKey()  Press any key to continue....
     */
    public static void pressAnyKey() {
        getInput();
    }
    //kiểm tra số nhập vào có lớn hon 0
    public static int getPositiveInteger(){
        while (true) {
            int result = getInteger();
            if (result > 0) {
                return result;
            }
            System.out.println("\u001B[33m" + ERROR_NUMBER);
        }
    }
    // kiểm tra id có đúng định dạng không
    public static String getSongId(){
        while (true) {
            String result = getString();
            if(result.startsWith("S")&&result.length()==4){
                return result;
            }
            System.out.println("\u001B[33m" + ERROR_SONGID);
        }
    }

    // Check password
    public static String getPassword(){
        while (true) {
            String result = getString();
            if(result.length()==6){
                return result;
            }
            System.out.println("\u001B[33m" + ERROR_PASSWORD);
        }
    }
    public static String checkConfirmPassword(String password){
        while (true) {
            String result = getString();
            if(result.equals(password)){
                return result;
            }
            System.out.println("\u001B[33m" + ERROR_CPASSWORD);
        }
    }
}