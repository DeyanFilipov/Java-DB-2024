package jsonprocessing.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jsonprocessing.services.AddressService;
import jsonprocessing.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

    @Bean("withNulls")
    @Primary
    public Gson createGSONWithNulls() {
        return new GsonBuilder()
//            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .setPrettyPrinting()
            .create();
    }

    @Bean("withoutNulls")
    public Gson createGSONWithoutNulls() {
        return new GsonBuilder()
//            .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public PersonService personService(
            AddressService addressService,
            @Value("${yourproject.yourkey.config1}") String config1
    ) {
        System.out.println(config1);
        return new PersonService(addressService);
    }

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation
            .buildDefaultValidatorFactory();

        return factory.getValidator();
    }


}
