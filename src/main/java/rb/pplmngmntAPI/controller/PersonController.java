package rb.pplmngmntAPI.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rb.pplmngmntAPI.dto.request.PersonDTO;
import rb.pplmngmntAPI.dto.response.MessageResponseDTO;
import rb.pplmngmntAPI.exception.PersonNotFoundException;
import rb.pplmngmntAPI.service.PersonService;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.savePerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll(@RequestBody @Valid PersonDTO personDTO) {
        return personService.findAll();
    }

    @GetMapping("/{personId}")
    public PersonDTO getPersonById(@PathVariable("personId") Long personId) throws PersonNotFoundException {
        return personService.getById(personId);
    }

    @PutMapping("/{personId}")
    public MessageResponseDTO updatePersonById(@PathVariable("personId") Long personId, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updatePerson(personId, personDTO);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonByID(@PathVariable("personId") Long personId) throws Exception, PersonNotFoundException {
        personService.deletePerson(personId);
    }

}
