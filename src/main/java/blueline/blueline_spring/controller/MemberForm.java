package blueline.blueline_spring.controller;


public class MemberForm {
    // createMembersForm에 있는 name 이 여기로 들어올 것임
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}