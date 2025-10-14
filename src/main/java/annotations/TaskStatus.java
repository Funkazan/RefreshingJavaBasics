package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TaskStatus {
    String assignedTo() default "Unassigned";
    String status() default "TODO";
    int estimatedHours() default 0;
}
