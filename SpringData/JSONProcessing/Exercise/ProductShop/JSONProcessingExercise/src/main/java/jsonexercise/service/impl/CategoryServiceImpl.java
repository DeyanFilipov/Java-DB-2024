package jsonexercise.service.impl;

import com.google.gson.Gson;
import jsonexercise.data.entities.Category;
import jsonexercise.data.entities.Product;
import jsonexercise.data.entities.repositories.CategoryRepository;
import jsonexercise.service.CategoryService;
import jsonexercise.service.dtos.export.CategoryByProductsDTO;
import jsonexercise.service.dtos.imports.CategorySeedDTO;
import jsonexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String FILE_PATH = "src/main/resources/json/categories.json";

    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            String jsonContent = new String(Files.readAllBytes(Path.of(FILE_PATH)));
//            this.gson.fromJson(new FileReader(FILE_PATH), CategorySeedDTO[].class);

            CategorySeedDTO[] categorySeedDTOS = this.gson.fromJson(jsonContent, CategorySeedDTO[].class);
            for (CategorySeedDTO categorySeedDTO : categorySeedDTOS) {
                if (!this.validationUtil.isValid(categorySeedDTO)) {
                    this.validationUtil.getViolations(categorySeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                this.categoryRepository.saveAndFlush(this.modelMapper.map(categorySeedDTO, Category.class));
            }
        }
    }

    @Override
    public List<CategoryByProductsDTO> getAllCategoriesByProducts() {
        return this.categoryRepository.findAllCategoriesByProducts()
                .stream()
                .map(c -> {
                    CategoryByProductsDTO dto = this.modelMapper.map(c, CategoryByProductsDTO.class);
                    dto.setProductsCount(c.getProducts().size());
                    BigDecimal sum = c.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).get();
                    dto.setTotalRevenue(sum);
                    dto.setAveragePrice(sum.divide(BigDecimal.valueOf(c.getProducts().size()), MathContext.DECIMAL64));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void printAllCategoriesByProducts() {
        String json = this.gson.toJson(this.getAllCategoriesByProducts());
        System.out.println(json);
    }
}
