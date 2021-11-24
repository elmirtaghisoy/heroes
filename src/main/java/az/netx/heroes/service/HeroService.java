package az.netx.heroes.service;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.Hero;
import az.netx.heroes.model.CustomFile;
import az.netx.heroes.model.request.HeroRequest;
import az.netx.heroes.model.response.HeroResponse;
import az.netx.heroes.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static az.netx.heroes.component.constraint.ApplicationConstraint.HERO;
import static az.netx.heroes.component.constraint.ApplicationConstraint.SOLDIER_DEFAULT_IMG_PATH;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final ObjectMapper objectMapper;

    public Paged<HeroResponse> searchHero(int page, int size, HeroSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size);

        Page<HeroResponse> postPage = heroRepository.findAll(
                SearchQueries.createHeroSpecification(searchRequest),
                pageRequest
        );

        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }

    public HeroResponse getHeroById(Long heroId) {
        return objectMapper.E2R(heroRepository.getById(heroId));
    }

    public void createHero(HeroRequest request) throws IOException {

        if (request.getImg().isEmpty()) {
            request.setFilePath(SOLDIER_DEFAULT_IMG_PATH);
        } else {
            CustomFile file = CustomFile.builder()
                    .category(HERO)
                    .file(request.getImg())
                    .build();
            request.setFilePath(FileService.saveSingle(file));
        }

        heroRepository.save(objectMapper.R2E(request));
    }

    public void updateHero(HeroRequest request) throws IOException {

        if (request.getImg().isEmpty()) {
            request.setFilePath(request.getFilePath());
        } else {
            CustomFile file = CustomFile.builder()
                    .category(HERO)
                    .file(request.getImg())
                    .build();
            request.setFilePath(FileService.saveSingle(file));
        }

        heroRepository.save(objectMapper.R2E(request));
    }

    public void deleteHero(Long heroId) {
        Hero entity = heroRepository.getById(heroId);
        entity.setStatus("DELETED");
        heroRepository.save(entity);
    }

    public List<HeroResponse> get3HeroByNotId(Long id) {
        return heroRepository.findTop4ByIdNotAndStatus(id, "ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }
}
