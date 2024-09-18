package entity;

public class MedicalTechnology {
    private int id;
    private String techName;
    private Double techPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public Double getTechPrice() {
        return techPrice;
    }

    public void setTechPrice(Double techPrice) {
        this.techPrice = techPrice;
    }
}
