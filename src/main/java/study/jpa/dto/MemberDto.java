package study.jpa.dto;

import lombok.Data;

@Data // getter, setter 둘다 존재
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

}
