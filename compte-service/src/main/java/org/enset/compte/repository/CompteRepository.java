package org.enset.compte.repository;

import org.enset.compte.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

public interface CompteRepository  extends JpaRepository<Compte,Long> {
}
