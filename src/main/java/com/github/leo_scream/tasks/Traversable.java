package com.github.leo_scream.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Specification: src/test/kotlin/com/github/leo_scream/tasks/TraversableSpec.kt
 */
public interface Traversable<T> {
    void forEach(final Consumer<T> consumer);

    default Traversable<T> filter(final Predicate<T> predicate) {
        throw new UnsupportedOperationException();
    }

    default <R> Traversable<R> map(final Function<T, R> mapper) {
        if (mapper == null) throw new NullPointerException();
        return consumer -> forEach(item -> consumer.accept(mapper.apply(item)));
    }

    default <R> Traversable<R> flatMap(final Function<T, List<R>> mapper) {
        throw new UnsupportedOperationException();
    }

    default List<T> toList() {
        List<T> list = new ArrayList<>();
        this.forEach(list::add);
        return list;
    }

    static <T> Traversable<T> from(final List<T> list) {
        return list::forEach;
    }
}
