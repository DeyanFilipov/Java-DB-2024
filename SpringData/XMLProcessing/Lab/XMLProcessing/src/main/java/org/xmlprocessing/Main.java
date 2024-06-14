package org.xmlprocessing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.xmlprocessing.models.AddressDTO;
import org.xmlprocessing.models.PersonDTO;
import org.xmlprocessing.models.PhoneBook;
import org.xmlprocessing.models.PhoneNumber;

import java.util.List;

@Component
public class Main implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        JAXBContext personContext = JAXBContext.newInstance(PersonDTO.class);
        Marshaller personMarshaller = personContext.createMarshaller();

        personMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        AddressDTO addressDTO = new AddressDTO("BG", "Svishtov");
        PersonDTO person = new PersonDTO("First", "Last", 12 ,addressDTO);
        personMarshaller.marshal(person, System.out);

//        Unmarshaller personUnmarshaller = personContext.createUnmarshaller();
//        PersonDTO parsed = (PersonDTO) personUnmarshaller.unmarshal(System.in);

        JAXBContext bookContext = JAXBContext.newInstance(PhoneBook.class);
        Marshaller bookMarshaller = bookContext.createMarshaller();
        bookMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        PhoneNumber number1 = new PhoneNumber("Gosho", "12412");
        PhoneNumber number2 = new PhoneNumber("Pesho", "adnaf");
        PhoneNumber number3 = new PhoneNumber("Ivcho", "!@^#*@!");
        PhoneBook book = new PhoneBook(
                "Pesho",
                List.of("First", "Second", "Third"),
                List.of(number1, number2, number3
                ));
        bookMarshaller.marshal(book, System.out);

        Unmarshaller bookUnmarshaller = bookContext.createUnmarshaller();
        PhoneBook parsedBook = (PhoneBook) bookUnmarshaller.unmarshal(System.in);
        System.out.println();
    }
}
