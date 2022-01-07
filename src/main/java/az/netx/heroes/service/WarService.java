package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.War;
import az.netx.heroes.model.request.WarRequest;
import az.netx.heroes.model.response.WarResponse;
import az.netx.heroes.repository.WarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarService {

    private final WarRepository warRepository;
    private final ObjectMapper objectMapper;

    public List<WarResponse> getAllWar() {
        return warRepository.findAllByStatus("ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public List<WarResponse> findWarByNotInIds(List<Long> ids) {
        return warRepository.findAllByIdNotInAndStatus(ids, "ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void createWar(WarRequest request) {
        warRepository.save(objectMapper.R2E(request));
    }

    public void updateWar(WarRequest request) {
        warRepository.save(objectMapper.R2E(request));
    }

    public void deleteWar(Long warId) {
        War entity = warRepository.getById(warId);
        entity.setStatus("DELETED");
        warRepository.save(entity);
    }

    public WarResponse getWarById(Long id) {
        return objectMapper.E2R(warRepository.getById(id));
    }

}
