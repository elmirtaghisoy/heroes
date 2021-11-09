package az.netx.heroes.service;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.Hero;
import az.netx.heroes.model.request.HeroRequest;
import az.netx.heroes.model.response.HeroResponse;
import az.netx.heroes.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final ObjectMapper objectMapper;

    public void createHero(HeroRequest request) {
        heroRepository.save(objectMapper.R2E(request));
    }

    public HeroResponse getHeroById(Long heroId) {
        return objectMapper.E2R(heroRepository.getById(heroId));
    }

    public void updateHero(HeroRequest request) {
        heroRepository.save(objectMapper.R2E(request));
    }

    public void deleteHero(Long heroId) {
        Hero entity = heroRepository.getById(heroId);
        entity.setStatus("DELETED");
        heroRepository.save(entity);
    }

    public Paged<HeroResponse> searchHero(int page, int size, HeroSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size);

        Page<HeroResponse> postPage = new PageImpl<>(
                heroRepository.findAll(SearchQueries.createHeroSpecification(searchRequest), pageRequest)
                        .stream()
                        .map(objectMapper::E2R)
                        .collect(Collectors.toList())
        );

        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }

}
