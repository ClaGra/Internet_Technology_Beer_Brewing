package ch.fhnw.brew;

import ch.fhnw.brew.business.service.*;
import ch.fhnw.brew.data.domain.*;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
@Hidden // Exclude from Swagger UI
public class BrewingApplication {

    @Autowired private RecipeService recipeService;
    @Autowired private BrewingProtocolService brewingProtocolService;
    @Autowired private BottlingService bottlingService;
    @Autowired private InventoryService inventoryService;
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

        // Recipe
        Recipe ipaRecipe = new Recipe();
        ipaRecipe.setRecipeName("Hoppy IPA");
        ipaRecipe.setRecipeCategory(RecipeCategory.STRONG_BEER);
        ipaRecipe = recipeService.addRecipe(ipaRecipe);

        // Brewing Protocol
        BrewingProtocol protocol = new BrewingProtocol();
        protocol.setBrewingDate(new Date());
        protocol.setRecipe(ipaRecipe);
        protocol.setOriginalGravity(1.060f);
        protocol.setFinalGravity(1.012f);
        protocol.setFermentationTankNumber("FT-01");
        protocol.setFermentationTankTemperature(20.0f);
        protocol.setHopsType("Cascade");
        protocol.setHopsAmount(5.0f);
        protocol.setHopsTime(60);
        protocol.setMaltType("Pale Malt");
        protocol.setMaltAmount(10.0f);
        protocol.setYeastType("Ale Yeast");
        protocol.setYeastAmount(0.5f);
        protocol.setWaterTreatmentType("Gypsum");
        protocol.setWaterTreatmentAmount(1.5f);
        protocol.setTemperatureMashIn(67.0f);
        protocol.setTemperatureMash(67.0f);
        protocol.setTemperatureMashOut(75.0f);
        protocol.setWaterMainCast(200.0f);
        protocol.setWaterSparge1(20.0f);
        protocol.setWaterSparge2(15.0f);
        protocol.setFurtherAdditionType("Coriander");
        protocol.setFurtherAdditionAmount(0.2f);
        protocol.setFurtherAdditionTime(5);
        brewingProtocolService.addBrewingProtocol(protocol);

        // Bottling (expiration will be set automatically to bottlingDate + 180 days)
        Bottling bottling = new Bottling();
        bottling.setBottlingDate(new Date());
        bottling.setAmount(100);
        bottling.setBrewingProtocol(protocol);
        bottlingService.addBottling(bottling); // Automatically updates inventory

        // Order
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setBeerName(ipaRecipe.getRecipeName());
        order.setAmount(10);
        order.setCustomer(customer);
        orderService.addOrder(order); // Automatically decreases inventory
    }
}
