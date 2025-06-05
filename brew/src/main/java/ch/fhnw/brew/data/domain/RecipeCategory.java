package ch.fhnw.brew.data.domain;

public enum RecipeCategory {
    LIGHT_BEER("Light Beer"),
    REGULAR_AND_SPECIALITY_BEER("Regular and Speciality Beer"),
    STRONG_BEER("Strong Beer");
    
    private final String label;

    RecipeCategory(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }

    public static RecipeCategory fromLabel(String label) {
        for (RecipeCategory category : RecipeCategory.values()) {
            if (category.getLabel().equalsIgnoreCase(label)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No matching RecipeCategory for label: " + label);
    }
}
