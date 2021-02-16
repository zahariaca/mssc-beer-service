package guru.springframework.msscbeerservice.repositories;

import guru.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

// no need to annotate with @Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
