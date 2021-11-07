package az.netx.heroes.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostRequest {
    private Long id;

    @NotNull(message = "Paylaşımın başlığını daxil edin")
    @NotBlank(message = "Paylaşımın başlığını daxil edin")
    private String header;

    @NotNull(message = "Paylaşımın içəriyini daxil edin")
    @NotBlank(message = "Paylaşımın içəriyini daxil edin")
    private String context;

    private PostCategoryRequest category;
}
