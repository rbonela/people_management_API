package rb.pplmngmntAPI.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rb.pplmngmntAPI.dto.request.PersonDTO;
import rb.pplmngmntAPI.dto.response.MessageResponseDTO;
import rb.pplmngmntAPI.entity.Person;
import rb.pplmngmntAPI.exception.PersonNotFoundException;
import rb.pplmngmntAPI.mapper.PersonMapper;
import rb.pplmngmntAPI.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

//    private final PersonMapper personMapper;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO savePerson(PersonDTO personDTO) {
        return createResponse(alterPersonDB(personDTO), "added in");
    }

    public List<PersonDTO> findAll(){
        List<Person> allElements = personRepository.findAll();
        return allElements.stream()
                .map(personMapper::fromModel)
                .collect(Collectors.toList());
    }

    public PersonDTO getById(Long personId) throws PersonNotFoundException {
       Person person = verifyIfExists(personId);
        return personMapper.fromModel(person);
    }

    public MessageResponseDTO updatePerson(Long personId, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(personId);

        return createResponse(alterPersonDB(personDTO),"updated in");
    }

    public MessageResponseDTO deletePerson(Long personId) throws PersonNotFoundException {
        Person person = verifyIfExists(personId);
        personRepository.deleteById(personId);
        return createResponse(person,"deleted from");
    }

    private Person verifyIfExists(Long personId) throws PersonNotFoundException{
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
    }

    private Person alterPersonDB(PersonDTO personDTO) {
        Person alteredPerson = personMapper.toModel(personDTO);
        return personRepository.save(alteredPerson);
    }

    private MessageResponseDTO createResponse(Person person, String string) {
        return MessageResponseDTO
                .builder()
                .message("Person with name " + person.getFirstName() + ", last name "
                        + person.getLastName() + " and ID " + person.getId()  + "  " + string + " database" )
                .build();
    }
}
