package my_bank.test;

import java.lang.reflect.Field;

public class EnumConverterTest {
    public static <T extends Enum<T>> T convertStringToEnumTest(Class<T> enumClass, String value) {
        try {
            Field[] enumFields = enumClass.getDeclaredFields();
            for (Field field : enumFields) {
                if (field.isEnumConstant()) {
                    T enumValue = Enum.valueOf(enumClass, field.getName());
                    if (value.equals(enumValue.toString())) {
                        return enumValue;
                    }
                }
            }
        } catch (IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
        }
        return null; // Or handle appropriately based on your requirements
    }

    public static void main(String[] args) {
        String statusFromDB = "ACTIVE";
        Status status = convertStringToEnumTest(Status.class, statusFromDB);
        System.out.println("Converted Enum value: " + status);
    }
}
