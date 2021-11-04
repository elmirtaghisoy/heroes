package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.Message;
import az.netx.heroes.model.request.MessageRequest;
import az.netx.heroes.model.response.MessageResponse;
import az.netx.heroes.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    public List<MessageResponse> getAllMessages() {
        return messageRepository.findAll()
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public MessageResponse getMessageById(Long messageId) {
        Message entity = messageRepository.getById(messageId);
        entity.setUnread(true);
        messageRepository.save(entity);
        return objectMapper.E2R(messageRepository.getById(messageId));
    }

    public void createMessage(MessageRequest request) {
        messageRepository.save(objectMapper.R2E(request));
    }

    public void deleteMessage(Long messageId) {
        Message entity = messageRepository.getById(messageId);
        entity.setStatus("DELETED");
        messageRepository.save(entity);
    }
}
