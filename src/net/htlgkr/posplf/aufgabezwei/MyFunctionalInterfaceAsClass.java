package net.htlgkr.posplf.aufgabezwei;

public class MyFunctionalInterfaceAsClass implements MyFunctionalInterface{
    @Override
    public String foo(int a, int b) {
        return a + " + " + b;
    }
}
