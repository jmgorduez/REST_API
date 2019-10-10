package com.gestorinc.repository;

import com.gestorinc.repository.entity.LogInterfaz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInterfaceLogRepository extends CrudRepository<LogInterfaz, Long> {

    @Query(" select  max(log.id) + 1 from LogInterfaz log ")
    Optional<Long> nextSequence();
}
