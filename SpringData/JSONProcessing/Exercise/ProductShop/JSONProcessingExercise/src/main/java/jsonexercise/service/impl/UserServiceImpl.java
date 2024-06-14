package jsonexercise.service.impl;

import com.google.gson.Gson;
import jsonexercise.data.entities.User;
import jsonexercise.data.entities.repositories.UserRepository;
import jsonexercise.service.UserService;
import jsonexercise.service.dtos.export.*;
import jsonexercise.service.dtos.imports.UserSeedDTO;
import jsonexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private static final String FILE_PATH = "src/main/resources/json/users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        if (this.userRepository.count() == 0) {
            UserSeedDTO[] userSeedDTOS = this.gson.fromJson(new FileReader(FILE_PATH), UserSeedDTO[].class);

            for (UserSeedDTO userSeedDTO : userSeedDTOS) {
                if (!this.validationUtil.isValid(userSeedDTO)) {
                    this.validationUtil.getViolations(userSeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                this.userRepository.saveAndFlush(this.modelMapper.map(userSeedDTO, User.class));
            }
        }
    }

    @Override
    public List<UserSoldProductsDTO> getAllUsersAndSoldItems() {
        return this.userRepository.findAll()
                .stream()
                .filter(u ->
                        u.getSold().stream().anyMatch(p -> p.getBuyer() != null))
                .map(u -> {
                    UserSoldProductsDTO userDTO = this.modelMapper.map(u, UserSoldProductsDTO.class);
                    List<ProductSoldDTO> soldProductsDTO = u.getSold()
                            .stream()
                            .filter(p -> p.getBuyer() != null)
                            .map(p -> this.modelMapper.map(p, ProductSoldDTO.class))
                            .collect(toList());
                            userDTO.setSoldProducts(soldProductsDTO);
                    return userDTO;
                })
                .sorted(Comparator.comparing(UserSoldProductsDTO::getLastName).thenComparing(UserSoldProductsDTO::getFirstName))
                .toList();
    }

    @Override
    public void printAllUsersAndSoldItems() {
        String json = this.gson.toJson(this.getAllUsersAndSoldItems());
        System.out.println(json);
    }

    @Override
    public UserAndProductDTO getUserAndProductDTO() {
        UserAndProductDTO userAndProductDTO = new UserAndProductDTO();
        List<UserSoldDTO> userSoldDTOS = this.userRepository.findAll()
                .stream().filter(u -> !u.getSold().isEmpty())
                .map(u -> {
                    UserSoldDTO userSoldDTO = this.modelMapper.map(u, UserSoldDTO.class);
                    ProductSoldByUserDTO productSoldByUserDTO = new ProductSoldByUserDTO();

                    List<ProductInfoDTO> productInfoDTOS = u.getSold()
                            .stream()
                            .map(p -> this.modelMapper.map(p, ProductInfoDTO.class))
                            .collect(Collectors.toList());
                    productSoldByUserDTO.setProducts(productInfoDTOS);
                    productSoldByUserDTO.setCount(productInfoDTOS.size());

                    userSoldDTO.setSoldProducts(productSoldByUserDTO);
                    return userSoldDTO;
                })
                .sorted((a, b) -> {
                    int countA = a.getSoldProducts().getCount();
                    int countB = b.getSoldProducts().getCount();
                    return Integer.compare(countB, countA);
                })
                .collect(Collectors.toList());
        userAndProductDTO.setUsers(userSoldDTOS);
        userAndProductDTO.setUsersCount(userSoldDTOS.size());
        return userAndProductDTO;
    }

    @Override
    public void printGetUserAndProductDTO() {
        String json = this.gson.toJson(this.getUserAndProductDTO());
        System.out.println(json);
    }

}
