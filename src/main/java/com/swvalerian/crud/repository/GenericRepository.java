package com.swvalerian.crud.repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    List<T> getAll();
    ID getId(ID id);
}
