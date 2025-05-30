package ch.fhnw.brew;

import ch.fhnw.brew.business.service.*;
import ch.fhnw.brew.data.domain.*;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
@Hidden
public class BrewingApplication {

    @Autowired private RecipeService recipeService;
    @Autowired private BrewingProtocolService brewingProtocolService;
    @Autowired private BottlingService bottlingService;
    @Autowired private CustomerService customerService;
    @Autowired private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(BrewingApplication.class, args);
    }

    @PostConstruct
    private void initPlaceholderData() throws Exception {
        // === CUSTOMERS ===
        Customer customer1 = new Customer();
        customer1.setFirstName("Anna");
        customer1.setLastName("Brewer");
        customer1.setDateOfBirth(LocalDate.of(1990, 5, 15));
        customer1.setStreet("Hopfenweg 12");
        customer1.setZipCode("8000");
        customer1.setCity("Zurich");
        customer1.setEmail("anna@example.com");
        customer1.setPhone("0781234567");
        customer1.setGender(Gender.FEMALE);
        customerService.addCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Ben");
        customer2.setLastName("Hopkins");
        customer2.setDateOfBirth(LocalDate.of(1985, 8, 20));
        customer2.setStreet("Malzstrasse 3");
        customer2.setZipCode("4051");
        customer2.setCity("Basel");
        customer2.setEmail("ben.h@example.com");
        customer2.setPhone("0797654321");
        customer2.setGender(Gender.MALE);
        customerService.addCustomer(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Lina");
        customer3.setLastName("Maltz");
        customer3.setDateOfBirth(LocalDate.of(1992, 3, 9));
        customer3.setStreet("Brauergasse 7");
        customer3.setZipCode("3001");
        customer3.setCity("Bern");
        customer3.setEmail("lina.m@example.com");
        customer3.setPhone("0766543210");
        customer3.setGender(Gender.OTHER);
        customerService.addCustomer(customer3);


        // === RECIPES ===
        Recipe ipa = new Recipe();
        ipa.setRecipeName("Hoppy IPA");
        ipa.setRecipeCategory(RecipeCategory.STRONG_BEER);
        ipa = recipeService.addRecipe(ipa);

        Recipe lager = new Recipe();
        lager.setRecipeName("Smooth Lager");
        lager.setRecipeCategory(RecipeCategory.LIGHT_BEER);
        lager = recipeService.addRecipe(lager);

        Recipe paleAle = new Recipe();
        paleAle.setRecipeName("Crisp Pale Ale");
        paleAle.setRecipeCategory(RecipeCategory.LIGHT_BEER);
        paleAle = recipeService.addRecipe(paleAle);

        Recipe stout = new Recipe();
        stout.setRecipeName("Dark Stout");
        stout.setRecipeCategory(RecipeCategory.STRONG_BEER);
        stout = recipeService.addRecipe(stout);

        Recipe wheat = new Recipe();
        wheat.setRecipeName("Sunny Wheat");
        wheat.setRecipeCategory(RecipeCategory.LIGHT_BEER);
        wheat = recipeService.addRecipe(wheat);

        Recipe redAle = new Recipe();
        redAle.setRecipeName("Red Mountain Ale");
        redAle.setRecipeCategory(RecipeCategory.STRONG_BEER);
        redAle = recipeService.addRecipe(redAle);

        // === BREWING PROTOCOLS ===
        BrewingProtocol ipaProtocol = new BrewingProtocol();
        ipaProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now()));
        ipaProtocol.setRecipe(ipa);
        ipaProtocol.setOriginalGravity(1.060f);
        ipaProtocol.setFermentationTankNumber("FT-01");
        ipaProtocol.setFermentationTankTemperature(20.0f);
        ipaProtocol.setHopsType("Cascade");
        ipaProtocol.setHopsAmount(5.0f);
        ipaProtocol.setHopsTime(60);
        ipaProtocol.setMaltType("Pale Malt");
        ipaProtocol.setMaltAmount(10.0f);
        ipaProtocol.setYeastType("Ale Yeast");
        ipaProtocol.setYeastAmount(0.5f);
        ipaProtocol.setWaterTreatmentType("Gypsum");
        ipaProtocol.setWaterTreatmentAmount(1.5f);
        ipaProtocol.setTemperatureMashIn(67.0f);
        ipaProtocol.setTemperatureMash(67.0f);
        ipaProtocol.setTemperatureMashOut(75.0f);
        ipaProtocol.setWaterQuantityMainCast(200.0f);
        ipaProtocol.setWaterQuantitySparge1(20.0f);
        ipaProtocol.setWaterQuantitySparge2(15.0f);
        ipaProtocol.setFurtherAdditionType("Coriander");
        ipaProtocol.setFurtherAdditionAmount(0.2f);
        ipaProtocol.setFurtherAdditionTime(5);
        brewingProtocolService.addBrewingProtocol(ipaProtocol);

        BrewingProtocol lagerProtocol = new BrewingProtocol();
        lagerProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now()));
        lagerProtocol.setRecipe(lager);
        brewingProtocolService.addBrewingProtocol(lagerProtocol);

        BrewingProtocol paleAleProtocol = new BrewingProtocol();
        paleAleProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now().minusDays(1)));
        paleAleProtocol.setRecipe(paleAle);
        paleAleProtocol.setOriginalGravity(1.055f);
        paleAleProtocol.setFermentationTankNumber("FT-02");
        paleAleProtocol.setFermentationTankTemperature(19.0f);
        paleAleProtocol.setHopsType("Centennial");
        paleAleProtocol.setHopsAmount(4.5f);
        paleAleProtocol.setHopsTime(50);
        paleAleProtocol.setMaltType("Pilsner Malt");
        paleAleProtocol.setMaltAmount(9.0f);
        paleAleProtocol.setYeastType("American Ale");
        paleAleProtocol.setYeastAmount(0.6f);
        paleAleProtocol.setWaterTreatmentType("Calcium Chloride");
        paleAleProtocol.setWaterTreatmentAmount(1.2f);
        paleAleProtocol.setTemperatureMashIn(65.0f);
        paleAleProtocol.setTemperatureMash(66.0f);
        paleAleProtocol.setTemperatureMashOut(74.0f);
        paleAleProtocol.setWaterQuantityMainCast(210.0f);
        paleAleProtocol.setWaterQuantitySparge1(22.0f);
        paleAleProtocol.setWaterQuantitySparge2(12.0f);
        paleAleProtocol.setFurtherAdditionType("Basil");
        paleAleProtocol.setFurtherAdditionAmount(0.5f);
        paleAleProtocol.setFurtherAdditionTime(5);
        brewingProtocolService.addBrewingProtocol(paleAleProtocol);

        BrewingProtocol stoutProtocol = new BrewingProtocol();
        stoutProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now().minusDays(1)));
        stoutProtocol.setRecipe(stout);
        brewingProtocolService.addBrewingProtocol(stoutProtocol);

        BrewingProtocol wheatProtocol = new BrewingProtocol();
        wheatProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now().minusDays(2)));
        wheatProtocol.setRecipe(wheat);
        wheatProtocol.setOriginalGravity(1.050f);
        wheatProtocol.setFermentationTankNumber("FT-03");
        wheatProtocol.setFermentationTankTemperature(18.5f);
        wheatProtocol.setHopsType("Hallertau");
        wheatProtocol.setHopsAmount(3.0f);
        wheatProtocol.setHopsTime(45);
        wheatProtocol.setMaltType("Wheat Malt");
        wheatProtocol.setMaltAmount(8.0f);
        wheatProtocol.setYeastType("Wheat Yeast");
        wheatProtocol.setYeastAmount(0.4f);
        wheatProtocol.setWaterTreatmentType("Lactic Acid");
        wheatProtocol.setWaterTreatmentAmount(1.0f);
        wheatProtocol.setTemperatureMashIn(64.0f);
        wheatProtocol.setTemperatureMash(65.0f);
        wheatProtocol.setTemperatureMashOut(72.0f);
        wheatProtocol.setWaterQuantityMainCast(190.0f);
        wheatProtocol.setWaterQuantitySparge1(18.0f);
        wheatProtocol.setWaterQuantitySparge2(14.0f);
        wheatProtocol.setFurtherAdditionType("Chocolate");
        wheatProtocol.setFurtherAdditionAmount(0.2f);
        wheatProtocol.setFurtherAdditionTime(5);
        brewingProtocolService.addBrewingProtocol(wheatProtocol);

        BrewingProtocol redAleProtocol = new BrewingProtocol();
        redAleProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now().minusDays(2)));
        redAleProtocol.setRecipe(redAle);
        brewingProtocolService.addBrewingProtocol(redAleProtocol);

        // === BOTTLINGS ===
        Bottling ipaBottling = new Bottling();
        ipaBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        ipaBottling.setAmount(100);
        ipaBottling.setFinalGravity(1.012f);
        ipaBottling.setBrewingProtocol(ipaProtocol);
        bottlingService.addBottling(ipaBottling);

        Bottling lagerBottling = new Bottling();
        lagerBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        lagerBottling.setAmount(80);
        lagerBottling.setFinalGravity(1.008f);
        lagerBottling.setBrewingProtocol(lagerProtocol);
        bottlingService.addBottling(lagerBottling);

        Bottling paleAleBottling = new Bottling();
        paleAleBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        paleAleBottling.setAmount(90);
        paleAleBottling.setFinalGravity(1.010f);
        paleAleBottling.setBrewingProtocol(paleAleProtocol);
        bottlingService.addBottling(paleAleBottling);

        Bottling stoutBottling = new Bottling();
        stoutBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        stoutBottling.setAmount(70);
        stoutBottling.setFinalGravity(1.015f);
        stoutBottling.setBrewingProtocol(stoutProtocol);
        bottlingService.addBottling(stoutBottling);

        Bottling wheatBottling = new Bottling();
        wheatBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        wheatBottling.setAmount(85);
        wheatBottling.setFinalGravity(1.009f);
        wheatBottling.setBrewingProtocol(wheatProtocol);
        bottlingService.addBottling(wheatBottling);

        Bottling redAleBottling = new Bottling();
        redAleBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        redAleBottling.setAmount(75);
        redAleBottling.setFinalGravity(1.014f);
        redAleBottling.setBrewingProtocol(redAleProtocol);
        bottlingService.addBottling(redAleBottling);

        // === ORDERS ===
        OrderItem item1a = new OrderItem();
        item1a.setBeerName("Hoppy IPA");
        item1a.setAmount(10);

        OrderItem item1b = new OrderItem();
        item1b.setBeerName("Smooth Lager");
        item1b.setAmount(5);

        Order order1 = new Order();
        order1.setOrderDate(LocalDate.now());
        order1.setCustomer(customer1);
        order1.setItems(List.of(item1a, item1b));
        orderService.addOrder(order1);

        OrderItem item2a = new OrderItem();
        item2a.setBeerName("Crisp Pale Ale");
        item2a.setAmount(8);

        OrderItem item2b = new OrderItem();
        item2b.setBeerName("Dark Stout");
        item2b.setAmount(6);

        Order order2 = new Order();
        order2.setOrderDate(LocalDate.now());
        order2.setCustomer(customer2);
        order2.setItems(List.of(item2a, item2b));
        orderService.addOrder(order2);

        OrderItem item3a = new OrderItem();
        item3a.setBeerName("Sunny Wheat");
        item3a.setAmount(12);

        OrderItem item3b = new OrderItem();
        item3b.setBeerName("Red Mountain Ale");
        item3b.setAmount(7);

        Order order3 = new Order();
        order3.setOrderDate(LocalDate.now());
        order3.setCustomer(customer3);
        order3.setItems(List.of(item3a, item3b));
        orderService.addOrder(order3);
    }
}
