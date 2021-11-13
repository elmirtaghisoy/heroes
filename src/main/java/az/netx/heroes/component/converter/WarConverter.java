package az.netx.heroes.component.converter;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.model.request.WarRequest;
import az.netx.heroes.service.WarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WarConverter implements Converter<String, WarRequest> {

    private final WarService warService;
    private final ObjectMapper objectMapper;

    @Override
    public WarRequest convert(String id) {
        return objectMapper.R2R(warService.getWarById(Long.parseLong(id)));
    }

}
