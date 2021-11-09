package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.Reward;
import az.netx.heroes.model.request.RewardRequest;
import az.netx.heroes.model.response.RewardResponse;
import az.netx.heroes.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;
    private final ObjectMapper objectMapper;

    public List<RewardResponse> getAllReward() {
        return rewardRepository.findAllByStatus("ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void createReward(RewardRequest request) {
        rewardRepository.save(objectMapper.R2E(request));
    }

    public void updateReward(RewardRequest request) {
        rewardRepository.save(objectMapper.R2E(request));
    }

    public void deleteReward(Long rewardId) {
        Reward entity = rewardRepository.getById(rewardId);
        entity.setStatus("DELETED");
        rewardRepository.save(entity);
    }
}
