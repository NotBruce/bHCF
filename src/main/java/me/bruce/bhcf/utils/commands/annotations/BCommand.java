package me.bruce.bhcf.utils.commands.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface BCommand {

    String name() default "";

    String description() default "";

    String permission() default "";

    String[] aliases() default {};

    Class<?> helpCommand() default void.class;

    boolean help() default false;
    boolean playerOnly() default false;


}
