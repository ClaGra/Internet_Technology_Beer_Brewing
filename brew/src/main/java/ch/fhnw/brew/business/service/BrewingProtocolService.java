package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.BrewingProtocol;
import ch.fhnw.brew.data.domain.Recipe;
import ch.fhnw.brew.data.repository.BrewingProtocolRepository;
import ch.fhnw.brew.data.repository.BottlingRepository;
import ch.fhnw.brew.data.repository.RecipeRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrewingProtocolService {

    @Autowired
    private BrewingProtocolRepository brewingProtocolRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private BottlingRepository bottlingRepository;

    public BrewingProtocol addBrewingProtocol(BrewingProtocol protocol) {
        Integer recipeId = protocol.getRecipe().getRecipeID();
        Recipe fullRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NotFoundException("Recipe not found with ID: " + recipeId));
        protocol.setRecipe(fullRecipe);
        return brewingProtocolRepository.save(protocol);
    }

    public BrewingProtocol editBrewingProtocol(Integer id, BrewingProtocol updated) {
        BrewingProtocol existing = brewingProtocolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol not found"));

        existing.setBrewingDate(updated.getBrewingDate());
        existing.setOriginalGravity(updated.getOriginalGravity());
        existing.setFermentationTankNumber(updated.getFermentationTankNumber());
        existing.setFermentationTankTemperature(updated.getFermentationTankTemperature());
        existing.setHopsType(updated.getHopsType());
        existing.setHopsAmount(updated.getHopsAmount());
        existing.setHopsTime(updated.getHopsTime());
        existing.setMaltType(updated.getMaltType());
        existing.setMaltAmount(updated.getMaltAmount());
        existing.setYeastType(updated.getYeastType());
        existing.setYeastAmount(updated.getYeastAmount());
        existing.setWaterTreatmentType(updated.getWaterTreatmentType());
        existing.setWaterTreatmentAmount(updated.getWaterTreatmentAmount());
        existing.setTemperatureMash(updated.getTemperatureMash());
        existing.setTemperatureMashIn(updated.getTemperatureMashIn());
        existing.setTemperatureMashOut(updated.getTemperatureMashOut());
        existing.setWaterQuantityMainCast(updated.getWaterQuantityMainCast());
        existing.setWaterQuantitySparge1(updated.getWaterQuantitySparge1());
        existing.setWaterQuantitySparge2(updated.getWaterQuantitySparge2());
        existing.setFurtherAdditionType(updated.getFurtherAdditionType());
        existing.setFurtherAdditionAmount(updated.getFurtherAdditionAmount());
        existing.setFurtherAdditionTime(updated.getFurtherAdditionTime());

        Integer recipeId = updated.getRecipe().getRecipeID();
        Recipe fullRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NotFoundException("Recipe not found with ID: " + recipeId));
        existing.setRecipe(fullRecipe);

        return brewingProtocolRepository.save(existing);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBrewingProtocol(Integer id) {
        BrewingProtocol protocol = brewingProtocolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol does not exist"));

        Integer batchNr = protocol.getBatchNr(); // Correct value to check bottling linkage

        boolean isUsedInBottling = bottlingRepository.existsByBrewingProtocolBatchNr(batchNr);
        if (isUsedInBottling) {
            throw new IllegalStateException("Brewing Protocol cannot be deleted because it is referenced in bottling");
        }

        brewingProtocolRepository.deleteById(id);
    }


    public BrewingProtocol getBrewingProtocol(Integer id) {
        return brewingProtocolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol not found"));
    }

    public List<BrewingProtocol> getAllBrewingProtocols() {
        return brewingProtocolRepository.findAll();
    }
}
