import injector.Injector;
import somepackage.SomeBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InjectorTest {
    @Test
    public void testInjectionWithSomeImpl() {
        SomeBean sb = new Injector("depsA.properties").inject(new SomeBean());
        System.out.println("Вывод для SomeImpl:");
        assertDoesNotThrow(sb::foo);
    }

    @Test
    public void testInjectionWithOtherImpl() {
        SomeBean sb = new Injector("depsB.properties").inject(new SomeBean());
        System.out.println("Вывод для OtherImpl:");
        assertDoesNotThrow(sb::foo);
    }
}
