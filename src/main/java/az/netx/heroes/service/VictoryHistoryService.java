package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.model.CustomFile;
import az.netx.heroes.model.request.VictoryHistoryRequest;
import az.netx.heroes.model.response.VictoryHistoryResponse;
import az.netx.heroes.repository.VictoryHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static az.netx.heroes.component.constraint.ApplicationConstraint.VICTORY_HIST;

@Service
@RequiredArgsConstructor
public class VictoryHistoryService {

    private final VictoryHistoryRepository victoryHistoryRepository;
    private final ObjectMapper objectMapper;

    public VictoryHistoryResponse getHistById(Long id) {
        return objectMapper.E2R(victoryHistoryRepository.getById(id));
    }

    public List<VictoryHistoryResponse> getAllHist() {
        return victoryHistoryRepository.findAll()
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void updateHist(VictoryHistoryRequest request) throws IOException {
        if (request.getImg().isEmpty()) {
            request.setFilePath(request.getFilePath());
        } else {
            CustomFile file = CustomFile.builder()
                    .category(VICTORY_HIST)
                    .file(request.getImg())
                    .build();
            request.setFilePath(FileService.saveSingle(file));
        }
        victoryHistoryRepository.save(objectMapper.R2E(request));
    }
}
