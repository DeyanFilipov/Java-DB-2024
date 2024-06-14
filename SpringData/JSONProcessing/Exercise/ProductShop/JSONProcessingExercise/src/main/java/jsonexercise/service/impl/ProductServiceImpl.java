package jsonexercise.service.impl;

import com.google.gson.Gson;
import jsonexercise.data.entities.Category;
import jsonexercise.data.entities.Product;
import jsonexercise.data.entities.User;
import jsonexercise.data.entities.repositories.CategoryRepository;
import jsonexercise.data.entities.repositories.ProductRepository;
import jsonexercise.data.entities.repositories.UserRepository;
import jsonexercise.service.ProductService;
import jsonexercise.service.dtos.export.ProductInRangeDTO;
import jsonexercise.service.dtos.imports.ProductSeedDTO;
import jsonexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String FILE_PATH = "src/main/resources/json/products.json";

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        if (this.productRepository.count() == 0) {
            ProductSeedDTO[] productSeedDTOS = this.gson.fromJson(new FileReader(FILE_PATH), ProductSeedDTO[].class);

            for (ProductSeedDTO productSeedDTO : productSeedDTOS) {
                if (!this.validationUtil.isValid(productSeedDTO)) {
                    this.validationUtil.getViolations(productSeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                Product product = this.modelMapper.map(productSeedDTO, Product.class);
                product.setBuyer(getRandomUser(true));
                product.setSeller(getRandomUser(false));
                product.setCategories(getRandomCategories());

                this.productRepository.saveAndFlush(product);
            }
        }
    }

    @Override
    public List<ProductInRangeDTO> getAllProductsInRange(BigDecimal from, BigDecimal to) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(from, to)
                .stream()
                .map(p -> {
                    ProductInRangeDTO dto = this.modelMapper.map(p, ProductInRangeDTO.class);
                    dto.setSeller(p.getSeller().getFirstName() + " " + p.getSeller().getLastName());
                    return dto;
                })
                .sorted(Comparator.comparing(ProductInRangeDTO::getPrice))
                .collect(Collectors.toList());

    }

    @Override
    public void printAllProductsInRange(BigDecimal from, BigDecimal to) {
        System.out.println(this.gson.toJson(this.getAllProductsInRange(from, to)));
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int randomCount = ThreadLocalRandom.current().nextInt(1, 4);
        for (int i = 0; i < randomCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count() + 1);
            categories.add(this.categoryRepository.findById(randomId).get());
        }
        return categories;

    }

    private User getRandomUser(boolean isBuyer) {

        long randomId = ThreadLocalRandom.current().nextLong(1, this.userRepository.count() + 1);

        return isBuyer && randomId % 4 == 0 ? null : this.userRepository.findById(randomId).get();
    }
}
