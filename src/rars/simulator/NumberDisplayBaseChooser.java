package rars.simulator;


import rars.Globals;
import rars.util.Binary;




/**
 * Use to select base for displaying numbers.  Initially the
 * choices are only 10 (decimal) and 16 (hex).
 */
//I copied this class from initial rars.venus package with methods
//only needed for this command-app line application
//   Nikita, 27 Dec 2023

public class NumberDisplayBaseChooser {
    public static final int DECIMAL = 10;
    public static final int HEXADECIMAL = 16;
    public static final int ASCII = 0;
    private int base;


    /**
     * Retrieve the current number base.
     *
     * @return current number base, currently DECIMAL or HEXADECIMAL
     */
    public int getBase() {
        return base;
    }

    /**
     * Set the current number base.
     *
     * @param newBase The new number base.  Currently, if it is
     *                neither DECIMAL nor HEXADECIMAL, the base will not be changed.
     */
    public void setBase(int newBase) {
        if (newBase == DECIMAL || newBase == HEXADECIMAL) {
            base = newBase;
        }
    }


    /**
     * Produces a string form of an unsigned given the value and the
     * numerical base to convert it to.  This class
     * method can be used by anyone anytime.  If base is 16, result
     * is same as for formatNumber().  If base is 10, will produce
     * string version of unsigned value.  E.g. 0xffffffff will produce
     * "4294967295" instead of "-1".
     *
     * @param value the number to be converted
     * @param base  the numerical base to use (currently 10 or 16)
     * @return a String equivalent of the value rendered appropriately.
     */
    public static String formatUnsignedInteger(int value, int base) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            return Binary.intToHexString(value);
        } else {
            return Binary.unsignedIntToIntString(value);
        }
    }


    /**
     * Produces a string form of an integer given the value and the
     * numerical base to convert it to.  There is an instance
     * method that uses the internally stored base.  This class
     * method can be used by anyone anytime.
     *
     * @param value the number to be converted
     * @param base  the numerical base to use (currently 10 or 16)
     * @return a String equivalent of the value rendered appropriately.
     */
    public static String formatNumber(int value, int base) {
        String result;
        switch (base) {
            case HEXADECIMAL:
                result = Binary.intToHexString(value);
                break;
            case DECIMAL:
                result = Integer.toString(value);
                break;
            case ASCII:
                result = Binary.intToAscii(value);
                break;
            default:
                result = Integer.toString(value);
        }
        return result;
        //          if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
        //             return Binary.intToHexString(value);
        //          }
        //          else {
        //             return Integer.toString(value);
        //          }
    }

    public static String formatNumber(long value, int base) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            return Binary.longToHexString(value);
        } else {
            return Long.toString(value);
        }
    }

    /**
     * Produces a string form of a float given the value and the
     * numerical base to convert it to.  There is an instance
     * method that uses the internally stored base.  This class
     * method can be used by anyone anytime.
     *
     * @param value the number to be converted
     * @param base  the numerical base to use (currently 10 or 16)
     * @return a String equivalent of the value rendered appropriately.
     */
    public static String formatNumber(float value, int base) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            return Binary.intToHexString(Float.floatToIntBits(value));
        } else {
            return Float.toString(value);
        }
    }


    /**
     * Produces a string form of a double given the value and the
     * numerical base to convert it to.  There is an instance
     * method that uses the internally stored base.  This class
     * method can be used by anyone anytime.
     *
     * @param value the number to be converted
     * @param base  the numerical base to use (currently 10 or 16)
     * @return a String equivalent of the value rendered appropriately.
     */
    public static String formatNumber(double value, int base) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            long lguy = Double.doubleToLongBits(value);
            return Binary.intToHexString(Binary.highOrderLongToInt(lguy)) +
                    Binary.intToHexString(Binary.lowOrderLongToInt(lguy)).substring(2);
        } else {
            return Double.toString(value);
        }
    }

    /**
     * Produces a string form of a number given the value.  There
     * is also an class (static method) that uses a specified
     * base.
     *
     * @param value the number to be converted
     * @return a String equivalent of the value rendered appropriately.
     */
    public String formatNumber(int value) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            return Binary.intToHexString(value);
        } else {
            return new Integer(value).toString();
        }
    }

    /**
     * Produces a string form of an unsigned integer given the value.  There
     * is also an class (static method) that uses a specified base.
     * If the current base is 16, this produces the same result as formatNumber().
     *
     * @param value the number to be converted
     * @return a String equivalent of the value rendered appropriately.
     */
    public String formatUnsignedInteger(int value) {
        return formatUnsignedInteger(value, base);
    }


    /**
     * Produces a string form of a float given an integer containing
     * the 32 bit pattern and the numerical base to use (10 or 16).  If the
     * base is 16, the string will be built from the 32 bits.  If the
     * base is 10, the int bits will be converted to float and the
     * string constructed from that.  Seems an odd distinction to make,
     * except that contents of floating point registers are stored
     * internally as int bits.  If the int bits represent a NaN value
     * (of which there are many!), converting them to float then calling
     * formatNumber(float, int) above, causes the float value to become
     * the canonical NaN value 0x7fc00000.  It does not preserve the bit
     * pattern!  Then converting it to hex string yields the canonical NaN.
     * Not an issue if display base is 10 since result string will be NaN
     * no matter what the internal NaN value is.
     *
     * @param value the int bits to be converted to string of corresponding float.
     * @param base  the numerical base to use (currently 10 or 16)
     * @return a String equivalent of the value rendered appropriately.
     */
    public static String formatFloatNumber(int value, int base) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            return Binary.intToHexString(value);
        } else {
            return Float.toString(Float.intBitsToFloat(value));
        }
    }

    /**
     * Produces a string form of a double given a long containing
     * the 64 bit pattern and the numerical base to use (10 or 16).  If the
     * base is 16, the string will be built from the 64 bits.  If the
     * base is 10, the long bits will be converted to double and the
     * string constructed from that.  Seems an odd distinction to make,
     * except that contents of floating point registers are stored
     * internally as int bits.  If the int bits represent a NaN value
     * (of which there are many!), converting them to double then calling
     * formatNumber(double, int) above, causes the double value to become
     * the canonical NaN value.  It does not preserve the bit
     * pattern!  Then converting it to hex string yields the canonical NaN.
     * Not an issue if display base is 10 since result string will be NaN
     * no matter what the internal NaN value is.
     *
     * @param value the long bits to be converted to string of corresponding double.
     * @param base  the numerical base to use (currently 10 or 16)
     * @return a String equivalent of the value rendered appropriately.
     */
    public static String formatDoubleNumber(long value, int base) {
        if (base == NumberDisplayBaseChooser.HEXADECIMAL) {
            return Binary.longToHexString(value);
        } else {
            return Double.toString(Double.longBitsToDouble(value));
        }
    }

}
