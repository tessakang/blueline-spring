package blueline.blueline_spring.domain;

public class Member {
    // 요구사항- id 식별자, 이
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}