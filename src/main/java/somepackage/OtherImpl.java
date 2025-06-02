package somepackage;
/**
 * Альтернативная реализация {@link SomeInterface}, выводящая "B".
 */
public class OtherImpl implements SomeInterface {
    public void doSomething() {
        System.out.println("B");
    }
}