package com.greenbowl.greenbowlserver.common.utility;

import com.greenbowl.greenbowlserver.common.domain.TargetType;
import com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.invalidvalue.InvalidTargetTypeException;
import com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.invalidvalue.ParsingBooleanException;
import com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.numberformat.*;

import static com.greenbowl.greenbowlserver.common.domain.excpeption.ExceptionMessage.*;

public class FormatConverter {
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    public static Long parseToLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException nfe) {
            throw new ParsingLongException((String.format(PARSING_LONG_EXCEPTION_MESSAGE, number)));
        }
    }

    public static Integer parseToInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            throw new ParsingIntegerException((String.format(PARSING_INTEGER_EXCEPTION_MESSAGE, number)));
        }
    }

    public static Short parseToShort(String number) throws ParsingShortException {
        try {
            return Short.parseShort(number);
        } catch (NumberFormatException nfe) {
            throw new ParsingShortException((String.format(PARSING_SHORT_EXCEPTION_MESSAGE, number)));
        }
    }

    public static Byte parseToByte(String number) throws ParsingByteException {
        try {
            return Byte.parseByte(number);
        } catch (NumberFormatException nfe) {
            throw new ParsingByteException((String.format(PARSING_BYTE_EXCEPTION_MESSAGE, number)));
        }
    }

    public static Double parseToDouble(String primeNumber) {
        try {
            return Double.parseDouble(primeNumber);
        } catch (NumberFormatException nfe) {
            throw new ParsingDoubleException((String.format(PARSING_DOUBLE_EXCEPTION_MESSAGE, primeNumber)));
        }
    }

    public static Float parseToFloat(String primeNumber) {
        try {
            return Float.parseFloat(primeNumber);
        } catch (NumberFormatException nfe) {
            throw new ParsingFloatException((String.format(PARSING_FLOAT_EXCEPTION_MESSAGE, primeNumber)));
        }
    }

    public static boolean parseToBoolean(String value) {
        if (!value.equals(TRUE) && !value.equals(FALSE)) {
            throw new ParsingBooleanException((String.format(PARSING_BOOLEAN_EXCEPTION_MESSAGE, value)));
        }

        return Boolean.parseBoolean(value);
    }

    public static TargetType parseToTargetType(String targetType) {
        try {
            return TargetType.valueOf(targetType);
        } catch (IllegalArgumentException iae) {
            throw new InvalidTargetTypeException(String.format(INVALID_TARGET_TYPE_EXCEPTION_MESSAGE, targetType));
        }
    }

    public static String sanitizeFileName(String filename) {
        return filename.replaceAll("\\s+", "-")
                .replaceAll("[^a-zA-Z0-9._-]", "");
    }
}
