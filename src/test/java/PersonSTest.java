import PatShelter.model.Gender;
import PatShelter.model.Person;
import PatShelter.repository.PersonRep;
import PatShelter.service.PersonS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
class PersonSTest {
    private final PersonRep personRepository = Mockito.mock(PersonRep.class);
    private final PersonS personService = new PersonS(personRepository);

    private final Person mike = new Person(
            1,
            "Mikey",
            "Vazovsky",
            21,
            Gender.MALE
    );
    private final Person damian = new Person(
            2,
            "Damian",
            "Lillard",
            26,
            Gender.MALE
    );
    @Test
    void getPerson() {
        Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mike));
        Assertions.assertEquals(mike, personService.getPerson(1531453215).orElse(null));
        Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertNull(personService.getPerson(1531453215).orElse(null));
    }
    @Test
    void addPerson() {
        Mockito.when(personRepository.personIsExists(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.any()))
                .thenReturn(null).thenReturn(mike);
        Assertions.assertEquals(mike, personService.addPerson(damian));
        Mockito.when(personRepository.personIsExists(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.any()))
                .thenReturn(damian);
        Assertions.assertNull(personService.addPerson(damian));
    }
    @Test
    void updatePerson() {
        Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mike));
        Mockito.when(personRepository.personIsExists(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.any()))
                .thenReturn(null);
        Assertions.assertEquals(mike, personService.updatePerson(damian));
        Mockito.when(personRepository.personIsExists(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.any()))
                .thenReturn(damian);
        Assertions.assertNull(personService.updatePerson(damian));
    }
    @Test
    void removePerson() {
        Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mike));
        Assertions.assertEquals(mike, personService.removePerson(51543));
        Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertNull(personService.removePerson(1654351));
    }
}
