package com.java.Reflexion;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {

    private final Properties prop;

    public Injector(String pathToFile) throws IOException {
        prop = new Properties();
        prop.load(new FileReader(pathToFile));
    }

    public Object inject(Object object) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> classOfObject = object.getClass();
        for (Field field : classOfObject.getDeclaredFields()) {
            if (field.getAnnotation(AutoInjectable.class) != null) {
                    Class<?> propertyValue = Class.forName(prop.getProperty(field.toString().split(" ")[1]));
                    Object classInstance = propertyValue.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(object, classInstance);
                }
            }
        return object;
    }
}
