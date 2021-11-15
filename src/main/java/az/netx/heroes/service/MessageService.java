package az.netx.heroes.service;

import az.netx.heroes.component.criteria.MessageSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.Message;
import az.netx.heroes.model.request.MessageRequest;
import az.netx.heroes.model.response.MessageResponse;
import az.netx.heroes.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    public MessageResponse getMessageById(Long messageId) {
        Message entity = messageRepository.getById(messageId);
        entity.setStatus("READ");
        entity.setReadTs(LocalDateTime.now());
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

    public Paged<MessageResponse> searchMessage(int page, int size, MessageSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size, Sort.by("readTs").ascending());

        Page<MessageResponse> postPage = messageRepository.findAll(
                SearchQueries.createMessageSpecification(searchRequest),
                pageRequest
        );

        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }
}
