package me.stay1444;

import java.util.ArrayList;

public enum Modifier {
    Public,
    Protected,
    Private,
    Static,
    Final,
    Synchronized,
    Volatile,
    Abstract,
    Transient;

    public static Modifier[] fromInt(int modifier) {
        var values = new ArrayList<Modifier>();
        if (java.lang.reflect.Modifier.isAbstract(modifier)) {
            values.add(Modifier.Abstract);
        }

        if (java.lang.reflect.Modifier.isSynchronized(modifier)) {
            values.add(Modifier.Synchronized);
        }

        if (java.lang.reflect.Modifier.isVolatile(modifier)) {
            values.add(Modifier.Volatile);
        }

        if (java.lang.reflect.Modifier.isTransient(modifier)) {
            values.add(Modifier.Transient);
        }

        if (java.lang.reflect.Modifier.isStatic(modifier)) {
            values.add(Modifier.Static);
        }

        if (java.lang.reflect.Modifier.isFinal(modifier)) {
            values.add(Modifier.Final);
        }

        if (java.lang.reflect.Modifier.isProtected(modifier)) {
            values.add(Modifier.Protected);
        }

        if (java.lang.reflect.Modifier.isPrivate(modifier)) {
            values.add(Modifier.Private);
        }

        if (java.lang.reflect.Modifier.isPublic(modifier)) {
            values.add(Modifier.Public);
        }

        return values.toArray(new Modifier[0]);
    }
}
