package org.example;

import injector.Injector;
import somepackage.SomeBean;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String propertiesFile = "depsA.properties";
        SomeBean sb = new Injector(propertiesFile).inject(new SomeBean());
        sb.foo();
    }
}