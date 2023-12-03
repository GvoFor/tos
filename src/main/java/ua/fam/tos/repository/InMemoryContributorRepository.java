package ua.fam.tos.repository;

import org.springframework.stereotype.Repository;
import ua.fam.tos.domain.Contributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryContributorRepository implements ContributorRepository {

    private List<Contributor> contributors;

    public InMemoryContributorRepository() {
        contributors = new ArrayList<>();

        Contributor misha = new Contributor();
        misha.setUsername("misha");
        misha.setId(0);
        contributors.add(misha);

        Contributor masha = new Contributor();
        masha.setUsername("masha");
        masha.setId(1);
        contributors.add(masha);

        Contributor kate = new Contributor();
        kate.setUsername("kate");
        kate.setId(2);
        contributors.add(kate);

        Contributor jeka = new Contributor();
        jeka.setUsername("jeka");
        jeka.setId(3);
        contributors.add(jeka);
    }

    @Override
    public Optional<Contributor> findContributorByUsername(String username) {
        return contributors.stream()
                .filter(contributor -> contributor.getUsername().equals(username))
                .findAny();
    }
}
