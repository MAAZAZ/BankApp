package org.enset.compte.repository;

import org.enset.compte.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
