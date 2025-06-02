package somepackage;

import injector.AutoInjectable;

/**
 * Класс, содержащий зависимости, которые должны быть внедрены через аннотацию.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Вызывает методы у внедренных зависимостей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}