package az.netx.heroes.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostCategoryRequest {
    @NotNull(message = "Paylaşımın kateqoriyasını daxil edin")
    private Long id;
    private String categoryName;
}
