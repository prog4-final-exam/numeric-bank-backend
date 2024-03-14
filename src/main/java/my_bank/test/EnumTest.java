package my_bank.test;

import my_bank.model.Account;
import my_bank.model.Transfer;

import java.lang.reflect.Field;

public class EnumTest {


    public static void main(String[] args) throws IllegalAccessException {
        myEnumTest myEnumTestOne = my_bank.test.myEnumTest.ONE;

        Class<?> clazz = Transfer.class;
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getType().isEnum());
        }
    }
}
