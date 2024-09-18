package entity;

public class Employee {
    private int id;
    private String realName;
    private Integer deptmentId;
    private Integer registLevelId;
    private String noon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getDeptmentId() {
        return deptmentId;
    }

    public void setDeptmentId(Integer deptmentId) {
        this.deptmentId = deptmentId;
    }

    public Integer getRegistLevelId() {
        return registLevelId;
    }

    public void setRegistLevelId(Integer registLevelId) {
        this.registLevelId = registLevelId;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }
}
