package az.netx.heroes.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostCategoryRequest {

    private Long id;

    @NotNull(message = "Paylaşım kateqoriyasını daxil edin")
    @NotBlank(message = "Paylaşım kateqoriyasını daxil edin")
    private String categoryName;

    private String status = "ACTIVE";
}
