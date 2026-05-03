package guru.qa.domain;

public class Teacher {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGoodTeacher() {
        return isGoodTeacher;
    }

    public void setGoodTeacher(Boolean goodTeacher) {
        isGoodTeacher = goodTeacher;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    private Boolean isGoodTeacher;
    private Integer age;
    private Passport passport;

    public static class Passport {
        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        private Integer number;
    }
}
