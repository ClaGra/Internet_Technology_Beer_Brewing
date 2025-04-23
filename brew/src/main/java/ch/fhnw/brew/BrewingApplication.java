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
@Hidden // Exclude from Swagger UI
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
        // Customer
        Customer customer = new Customer();
        customer.setFirstName("Anna");
        customer.setLastName("Brewer");
        customer.setCity("Zurich");
        customer.setEmail("anna@example.com");
        customer.setPhone("0781234567");
        customerService.addCustomer(customer);

        // Recipe 1: IPA
        Recipe ipaRecipe = new Recipe();
        ipaRecipe.setRecipeName("Hoppy IPA");
        ipaRecipe.setRecipeCategory(RecipeCategory.STRONG_BEER);
        ipaRecipe = recipeService.addRecipe(ipaRecipe);

        // Recipe 2: Lager
        Recipe lagerRecipe = new Recipe();
        lagerRecipe.setRecipeName("Smooth Lager");
        lagerRecipe.setRecipeCategory(RecipeCategory.LIGHT_BEER);
        lagerRecipe = recipeService.addRecipe(lagerRecipe);

        // Brewing Protocol for IPA
        BrewingProtocol ipaProtocol = new BrewingProtocol();
        ipaProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now()));
        ipaProtocol.setRecipe(ipaRecipe);
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

        // Brewing Protocol for Lager
        BrewingProtocol lagerProtocol = new BrewingProtocol();
        lagerProtocol.setBrewingDate(java.sql.Date.valueOf(LocalDate.now()));
        lagerProtocol.setRecipe(lagerRecipe);
        brewingProtocolService.addBrewingProtocol(lagerProtocol);

        // Bottling for IPA
        Bottling ipaBottling = new Bottling();
        ipaBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        ipaBottling.setAmount(100);
        ipaBottling.setFinalGravity(1.012f); // <-- Set Final Gravity
        ipaBottling.setBrewingProtocol(ipaProtocol);
        bottlingService.addBottling(ipaBottling);

        // Bottling for Lager
        Bottling lagerBottling = new Bottling();
        lagerBottling.setBottlingDate(java.sql.Date.valueOf(LocalDate.now()));
        lagerBottling.setAmount(80);
        lagerBottling.setFinalGravity(1.008f); // <-- Set Final Gravity
        lagerBottling.setBrewingProtocol(lagerProtocol);
        bottlingService.addBottling(lagerBottling);

        // Order with multiple beer types
        OrderItem ipaItem = new OrderItem();
        ipaItem.setBeerName("Hoppy IPA");
        ipaItem.setAmount(10);

        OrderItem lagerItem = new OrderItem();
        lagerItem.setBeerName("Smooth Lager");
        lagerItem.setAmount(5);

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setItems(List.of(ipaItem, lagerItem));

        orderService.addOrder(order);
    }
}
