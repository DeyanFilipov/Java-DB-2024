package jsonprocessing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonprocessing.dtos.AddressDTO;
import jsonprocessing.dtos.PersonDTO;
import jsonprocessing.entities.Person;
import jsonprocessing.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Main implements CommandLineRunner {

    private Gson gson;

    private PersonService personService;

    private int connectionCount;

    private ModelMapper modelMapper;

    public Main(
            @Qualifier("withoutNulls") Gson gson,
            @Value("${yourproject.yourkey.connectionCount}") int connectionCount,
            PersonService personService,
            ModelMapper modelMapper) {
        this.gson = gson;
        this.personService = personService;
        this.connectionCount = connectionCount;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
//        Gson gson = new GsonBuilder()
////                .excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
//                .setPrettyPrinting()
//                .create();
//
//            printJson(gson);
            readJson(gson);
    }

    private void readJson(Gson gson) {
        String json = """
                [
                    {
                        "firstName": "First",
                        "lastName": null,
                        "age": 25,
                        "isMarried": true,
                        "lotteryNumbers": [
                          1,
                          2,
                          5,
                          22,
                          45
                        ],
                        "address": {
                          "country": "Bg",
                          "city": "Burgas"
                        }
                    },
                    {
                        "firstName": "Second",
                        "lastName": null,
                        "age": 55,
                        "isMarried": false,
                        "lotteryNumbers": [
                          22,
                          45
                        ],
                        "address": {
                          "country": "Bg",
                          "city": "Burgas"
                        }
                    }
                ]
                """;

        PersonDTO[] personDTO = gson.fromJson(json, PersonDTO[].class);
        List<Person> realPeople = new ArrayList<>();

        for (PersonDTO dto : personDTO) {
            Person person = modelMapper.map(dto, Person.class);
            realPeople.add(person);
        }

        for (Person realPerson : realPeople) {
            System.out.println(realPerson);
        }
    }

    private void printJson(Gson gson) {
        PersonDTO personDTO = new PersonDTO(
                "First",
                null,
                25,
                true,
                List.of(1, 2, 5, 22, 45),
                new AddressDTO("Bg", "Burgas")
        );

//        String json = gson.toJson(personDTO);

        String json = gson.toJson(List.of(personDTO, personDTO));
        System.out.println(json);
    }
}

