package org.xmlprocessing.models;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO {

    @XmlElement
    private String firstName;

    @XmlElement(name = "lastName")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    public String number = "123102846";

    private AddressDTO address;

    public PersonDTO() {}

    public PersonDTO(String firstName, String lastName, int age, AddressDTO address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }


}
