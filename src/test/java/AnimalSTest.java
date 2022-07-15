import PatShelter.model.Animal;
import PatShelter.model.Gender;
import PatShelter.model.Species;
import PatShelter.repository.AnimalRep;
import PatShelter.service.AnimalS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
class AnimalSTest {
    private final AnimalRep animalRepository = Mockito.mock(AnimalRep.class);
    private final AnimalS animalService = new AnimalS(animalRepository);
    private final Animal shainy = new Animal(
            1,
            Species.DOG,
            Gender.FEMALE,
            10,
            "Shayne"
    );
    private final Animal petro = new Animal(
            2,
            Species.DOG,
            Gender.MALE,
            5,
            "Petro"
    );
    @Test
    void getAnimal() {
        Mockito.when(animalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(shainy));
        Assertions.assertEquals(shainy, animalService.getAnimal(100000000).orElse(null));
        Mockito.when(animalRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertNull(animalService.getAnimal(100000000).orElse(null));
    }
    @Test
    void addAnimal() {
        Mockito.when(animalRepository.animalIsExists(Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null).thenReturn(shainy);
        Assertions.assertEquals(shainy, animalService.addAnimal(petro));
        Mockito.when(animalRepository.animalIsExists(Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(shainy);
        Assertions.assertNull(animalService.addAnimal(petro));
    }
    @Test
    void updateAnimal() {
        Mockito.when(animalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(shainy));
        Mockito.when(animalRepository.animalIsExists(Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        Assertions.assertEquals(shainy, animalService.updateAnimal(petro));
        Mockito.when(animalRepository.animalIsExists(Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(petro);
        Assertions.assertNull(animalService.updateAnimal(petro));
    }
    @Test
    void removeAnimal() {
        Mockito.when(animalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(shainy));
        Assertions.assertEquals(shainy, animalService.removeAnimal(10000000));
        Mockito.when(animalRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertNull(animalService.removeAnimal(10000000));
    }
}