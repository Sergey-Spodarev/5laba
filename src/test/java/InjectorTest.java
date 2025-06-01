import injector.Injector;
import org.junit.Test;
import somepackage.SomeBean;

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
