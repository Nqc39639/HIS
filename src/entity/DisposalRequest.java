package entity;

public class DisposalRequest {
    private Integer registerId;
    private Integer medicalTechnologyId;
    private String creationTime;
    private String disposalState;
    private Integer expenseState;
    private String techName;
    private Double techPrice;
    private String caseNumber;
    private String realName;
    private String cardNumber;
    private String gender;
    private Integer age;

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Integer getMedicalTechnologyId() {
        return medicalTechnologyId;
    }

    public void setMedicalTechnologyId(Integer medicalTechnologyId) {
        this.medicalTechnologyId = medicalTechnologyId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getDisposalState() {
        return disposalState;
    }

    public void setDisposalState(String disposalState) {
        this.disposalState = disposalState;
    }

    public Integer getExpenseState() {
        return expenseState;
    }

    public void setExpenseState(Integer expenseState) {
        this.expenseState = expenseState;
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

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
