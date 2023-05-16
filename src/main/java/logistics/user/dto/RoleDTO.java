package logistics.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
    private Long id;
    private String roleName;
    private List<Long> userIds;
}