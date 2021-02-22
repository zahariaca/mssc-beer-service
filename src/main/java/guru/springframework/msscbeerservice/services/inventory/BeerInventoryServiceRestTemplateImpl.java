package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@RequiredArgsConstructor
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private final String INVENTORY_PATH =  "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;
    private String beerInventoryServiceHost;

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH, GET, null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, (Object) beerId);

        // sum from inventory list
        Integer onHand = requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
