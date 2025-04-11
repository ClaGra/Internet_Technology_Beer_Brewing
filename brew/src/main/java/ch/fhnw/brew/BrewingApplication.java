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
        ipaRecipe.setRecipeCategoryName("India Pale Ale");
        ipaRecipe = recipeService.addRecipe(ipaRecipe);

        // BrewingProtocol
        BrewingProtocol protocol = new BrewingProtocol();
        protocol.setBrewingDate(new Date());
        protocol.setRecipe(ipaRecipe);
        protocol.setOriginalGravity(1.060f);
        protocol.setFinalGravity(1.012f);
        protocol.setFermentationTankNumber("FT-01");
        protocol.setHopsType("Cascade");
        protocol.setHopsAmount(5.0f);
        protocol.setMaltType("Pale Malt");
        protocol.setMaltAmount(10.0f);
        protocol.setYeastType("Ale Yeast");
        protocol.setYeastAmount(0.5f);
        brewingProtocolService.addBrewingProtocol(protocol);

        // Bottling
        Bottling bottling = new Bottling();
        bottling.setBottlingDate(new Date());
        bottling.setExpirationDate(new Date(System.currentTimeMillis() + 31556952000L)); // +1 year
        bottling.setAmount(100);
        bottling.setBrewingProtocol(protocol);
        bottlingService.addBottling(bottling); // Will auto-update inventory

        // Inventory (optional manual initialization)
        inventoryService.updateInventoryAmount("Hoppy IPA", 100);

        // Order
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setBeerName("Hoppy IPA");
        order.setAmount(10);
        order.setCustomer(customer);
        orderService.addOrder(order); // Will decrease inventory
    }
}
