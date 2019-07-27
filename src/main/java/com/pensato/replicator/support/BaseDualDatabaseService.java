package com.pensato.replicator.support;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class BaseDualDatabaseService<T extends BaseEntity<T,ID>, ID, R extends PagingAndSortingRepository<T, ID>> {

    private R primeRepository;
    private R replicaRepository;

    public void initializeReplication() {
        List<T> items = findAll();
        List<T> replicas = findAllInReplica();

        String className = "";
        try {
            Class<T> clazz = resolveClass(getClass());
            if (clazz != null) {
                clazz.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(T entity: items) {
            if(!replicas.contains(entity)) {
                createOrUpdateInReplica(entity.createCopy());
                System.out.println("---> Entity " + className + ":" + entity.getId() + " was successfully replicated.");
            }
        }
    }

    @Transactional(transactionManager="primeTransactionManager")
    public List<T> findAll()
    {
        List<T> target = new ArrayList<>();
        primeRepository.findAll().forEach(target::add);
        return target;
    }

    @Transactional(transactionManager="replicaTransactionManager")
    public List<T> findAllInReplica()
    {
        List<T> target = new ArrayList<>();
        replicaRepository.findAll().forEach(target::add);
        return target;
    }

    @Transactional(transactionManager="replicaTransactionManager")
    public void createOrUpdateInReplica(T entity)
    {
        replicaRepository.save(entity);
    }

    /**
     * Resolve the single type argument of the given generic interface against
     * the given target class which is assumed to implement the generic interface
     * and possibly declare a concrete type for its type variable.
     * @param clazz the target class to check against
     * @return the resolved type of the argument, or {@code null} if not resolvable
     */
    @Nullable
    @SuppressWarnings("unchecked")
    private Class<T> resolveClass(Class<?> clazz) {
        ResolvableType resolvableType = ResolvableType.forClass(clazz).as(BaseDualDatabaseService.class);
        if (!resolvableType.hasGenerics()) {
            return null;
        }
        if(resolvableType.getGenerics().length == 1) {
            return (Class<T>) resolvableType.getGeneric().resolve();
        } else {
            // In case of multiple generic interfaces, the line below expects Entity to be the first argument.
            return (Class<T>) resolvableType.getGenerics()[0].resolve();
        }
    }

}
