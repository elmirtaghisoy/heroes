package az.netx.heroes.component.annotation;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsImage.CoverImageValidator.class)
public @interface IsImage {
    String message() default "Invalid file type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CoverImageValidator implements ConstraintValidator<IsImage, MultipartFile> {
        @Override
        public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
            try {
                String[] extensions = new String[]{"jpg", "jpeg", "png", "img"};
                for (String extension : extensions) {
                    if (file.isEmpty() || file == null || Files.getFileExtension(file.getOriginalFilename()).equals(extension)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception ex) {
                return true;
            }
        }
    }
}
