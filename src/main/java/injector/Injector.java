package injector;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Утилитный класс для автоматического внедрения зависимостей через аннотацию {@link AutoInjectable}.
 */
public class Injector {
    private final Properties appProperties;

    /**
     * Загружает свойства из указанного файла ресурсов.
     *
     * @param propertyFileName имя файла свойств в директории resources
     */
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

    /**
     * Внедряет зависимости в указанный объект.
     *
     * @param targetObject объект, в который нужно внедрить зависимости
     * @return объект с внедрёнными зависимостями
     */
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