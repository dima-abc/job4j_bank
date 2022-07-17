package ru.job4j.bank.repository;

import ru.job4j.bank.model.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3.4.8. Rest
 * 5. Обработка исключений и Spring REST [#504797]
 * BankService из уровня "Стажер".
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 17.07.2022
 */
public abstract class Store<T extends Id> {
    protected Map<Integer, T> store = new ConcurrentHashMap<>();
    protected AtomicInteger idGenerate = new AtomicInteger(0);

    public T saveOrUpdate(T model) {
        if (model.getId() < 1) {
            model.setId(idGenerate.incrementAndGet());
        }
        return store.merge(model.getId(), model, (v1, v2) -> v2);
    }

    public Optional<T> delete(int id) {
        return Optional.ofNullable(store.remove(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<T> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }
}
