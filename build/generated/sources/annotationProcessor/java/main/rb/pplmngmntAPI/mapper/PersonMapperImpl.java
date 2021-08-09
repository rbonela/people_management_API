package rb.pplmngmntAPI.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rb.pplmngmntAPI.dto.request.PersonDTO;
import rb.pplmngmntAPI.dto.request.PersonDTO.PersonDTOBuilder;
import rb.pplmngmntAPI.dto.request.PhoneDTO;
import rb.pplmngmntAPI.entity.Person;
import rb.pplmngmntAPI.entity.Person.PersonBuilder;
import rb.pplmngmntAPI.entity.Phone;
import rb.pplmngmntAPI.entity.Phone.PhoneBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-07T16:19:55-0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 16.0.2 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toModel(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        PersonBuilder person = Person.builder();

        if ( personDTO.getBirthDate() != null ) {
            person.birthDate( LocalDate.parse( personDTO.getBirthDate(), DateTimeFormatter.ofPattern( "dd-MM-yyyy" ) ) );
        }
        person.id( personDTO.getId() );
        person.firstName( personDTO.getFirstName() );
        person.lastName( personDTO.getLastName() );
        person.cpf( personDTO.getCpf() );
        person.phoneList( phoneDTOListToPhoneList( personDTO.getPhoneList() ) );

        return person.build();
    }

    @Override
    public PersonDTO fromModel(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTOBuilder personDTO = PersonDTO.builder();

        personDTO.id( person.getId() );
        personDTO.firstName( person.getFirstName() );
        personDTO.lastName( person.getLastName() );
        personDTO.cpf( person.getCpf() );
        if ( person.getBirthDate() != null ) {
            personDTO.birthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( person.getBirthDate() ) );
        }
        personDTO.phoneList( phoneListToPhoneDTOList( person.getPhoneList() ) );

        return personDTO.build();
    }

    protected Phone phoneDTOToPhone(PhoneDTO phoneDTO) {
        if ( phoneDTO == null ) {
            return null;
        }

        PhoneBuilder phone = Phone.builder();

        phone.id( phoneDTO.getId() );
        phone.phoneType( phoneDTO.getPhoneType() );
        phone.phoneNumber( phoneDTO.getPhoneNumber() );

        return phone.build();
    }

    protected List<Phone> phoneDTOListToPhoneList(List<PhoneDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneDTO phoneDTO : list ) {
            list1.add( phoneDTOToPhone( phoneDTO ) );
        }

        return list1;
    }

    protected PhoneDTO phoneToPhoneDTO(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDTO phoneDTO = new PhoneDTO();

        phoneDTO.setId( phone.getId() );
        phoneDTO.setPhoneType( phone.getPhoneType() );
        phoneDTO.setPhoneNumber( phone.getPhoneNumber() );

        return phoneDTO;
    }

    protected List<PhoneDTO> phoneListToPhoneDTOList(List<Phone> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneDTO> list1 = new ArrayList<PhoneDTO>( list.size() );
        for ( Phone phone : list ) {
            list1.add( phoneToPhoneDTO( phone ) );
        }

        return list1;
    }
}
