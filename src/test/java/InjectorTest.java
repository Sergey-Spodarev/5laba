import injector.Injector;
import somepackage.SomeBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Тесты для проверки работы механизма внедрения зависимостей.
 */
public class InjectorTest {
    /**
     * Проверяет, что зависимости корректно внедряются из файла depsA.properties.
     */
    @Test
    public void testInjectionWithSomeImpl() {
        SomeBean sb = new Injector("depsA.properties").inject(new SomeBean());
        System.out.println("Вывод для SomeImpl:");
        assertDoesNotThrow(sb::foo);
    }

    /**
     * Проверяет, что зависимости корректно внедряются из файла depsB.properties.
     */
    @Test
    public void testInjectionWithOtherImpl() {
        SomeBean sb = new Injector("depsB.properties").inject(new SomeBean());
        System.out.println("Вывод для OtherImpl:");
        assertDoesNotThrow(sb::foo);
    }
}
