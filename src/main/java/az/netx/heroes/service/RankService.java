package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.Rank;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.response.RankResponse;
import az.netx.heroes.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankService {

    private final RankRepository rankRepository;
    private final ObjectMapper objectMapper;

    public List<RankResponse> getAllRank() {
        return rankRepository.findAllByStatus("ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void createRank(RankRequest request) {
        rankRepository.save(objectMapper.R2E(request));
    }

    public void updateRank(RankRequest request) {
        rankRepository.save(objectMapper.R2E(request));
    }

    public void deleteRank(Long rankId) {
        Rank entity = rankRepository.getById(rankId);
        entity.setStatus("DELETED");
        rankRepository.save(entity);
    }

    public RankResponse getRank(Long id) {
        return objectMapper.E2R(rankRepository.getById(id));
    }

}
