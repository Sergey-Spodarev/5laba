package somepackage;
/**
 * Базовая реализация {@link SomeInterface}, выводящая "A".
 */
public class SomeImpl implements SomeInterface {
    public void doSomething() {
        System.out.println("A");
    }
}