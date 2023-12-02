package ua.fam.tos.repository;

import ua.fam.tos.domain.Contributor;

import java.util.Optional;

public interface ContributorRepository {

    Optional<Contributor> findContributorByUsername(String username);

}
