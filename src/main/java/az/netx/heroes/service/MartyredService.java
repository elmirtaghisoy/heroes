package az.netx.heroes.service;

import az.netx.heroes.component.criteria.MartyredSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.Martyred;
import az.netx.heroes.model.request.MartyredRequest;
import az.netx.heroes.model.response.MartyredResponse;
import az.netx.heroes.repository.MartyredRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MartyredService {

    private final MartyredRepository martyredRepository;
    private final ObjectMapper objectMapper;

    public void createMartyred(MartyredRequest request) {
        martyredRepository.save(objectMapper.R2E(request));
    }

    public MartyredResponse getMartyredById(Long martyredId) {
        return objectMapper.E2R(martyredRepository.getById(martyredId));
    }

    public void updateMartyred(MartyredRequest request) {
        martyredRepository.save(objectMapper.R2E(request));
    }

    public void deleteMartyred(Long martyredId) {
        Martyred entity = martyredRepository.getById(martyredId);
        entity.setStatus("DELETED");
        martyredRepository.save(entity);
    }

    public Paged<MartyredResponse> searchMartyred(int page, int size, MartyredSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size);

        Page<MartyredResponse> postPage = martyredRepository.findAll(
                SearchQueries.createMartyredSpecification(searchRequest),
                pageRequest
        );

        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }
}
