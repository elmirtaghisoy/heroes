package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.Contact;
import az.netx.heroes.model.request.ContactRequest;
import az.netx.heroes.model.response.ContactResponse;
import az.netx.heroes.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ObjectMapper objectMapper;

    public List<ContactResponse> getAllContact() {
        return contactRepository.findAllByStatusTrue()
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void createContact(ContactRequest request) {
        contactRepository.save(objectMapper.R2E(request));
    }

    public void updateContact(ContactRequest request) {
        contactRepository.save(objectMapper.R2E(request));
    }

    public void deleteContact(Long rankId) {
        Contact entity = contactRepository.getById(rankId);
        entity.setStatus("DELETED");
        contactRepository.save(entity);
    }

}
