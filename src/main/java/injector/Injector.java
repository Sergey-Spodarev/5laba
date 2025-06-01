package injector;

import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private final Properties appProperties;

    public Injector(String propertyFileName) {
        appProperties = new Properties();
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Файл " + propertyFileName + " не найден в resources!");
            }
            appProperties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке файла " + propertyFileName, e);
        }
    }

    public <T> T inject(T targetObject) {
        Class<?> targetClass = targetObject.getClass();

        for (Field field : targetClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String interfaceCanonicalName = fieldType.getCanonicalName();

                String implementationClassName = appProperties.getProperty(interfaceCanonicalName);

                if (implementationClassName == null) {
                    throw new RuntimeException("Не найдена реализация для интерфейса: " + interfaceCanonicalName);
                }

                try {
                    Class<?> implementationClass = Class.forName(implementationClassName);
                    Object dependencyInstance = implementationClass.getDeclaredConstructor().newInstance();

                    field.setAccessible(true);
                    field.set(targetObject, dependencyInstance);
                } catch (Exception e) {
                    throw new RuntimeException("Ошибка при внедрении зависимости в поле: " + field.getName(), e);
                }
            }
        }

        return targetObject;
    }
}