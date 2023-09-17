package fr.codelines.multidb.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
public @interface Column {
    String name();
    boolean nullable() default true;
}
