package az.netx.heroes.component.converter;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.model.request.RewardRequest;
import az.netx.heroes.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RewardConverter implements Converter<String, RewardRequest> {

    private final RewardService rewardService;
    private final ObjectMapper objectMapper;

    @Override
    public RewardRequest convert(String id) {
        return objectMapper.R2R(rewardService.getRewardById(Long.parseLong(id)));
    }

}
