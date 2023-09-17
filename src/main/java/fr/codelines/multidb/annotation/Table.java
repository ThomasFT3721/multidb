package fr.codelines.multidb.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
public @interface Table {
    String name();
}
