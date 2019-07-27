package com.pensato.replicator.support;

public interface BaseEntity<T, ID> {
    ID getId();
    T createCopy();
}
