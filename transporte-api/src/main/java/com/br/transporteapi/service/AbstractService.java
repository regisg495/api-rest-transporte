package com.br.transporteapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface AbstractService<T, F> {

    public List<T> findAll();

    public Page<T> findAll(Pageable pageable);

    public T findById(Long id);

    @Transactional
    public void deleteById(Long id);

    @Transactional
    public T save(F form);

    @Transactional
    public T edit(Long id, F form);
}
