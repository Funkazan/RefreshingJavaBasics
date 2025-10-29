/**
 * Annotation to attach simple task metadata to a method.
 *
 * <p>Use this annotation to indicate who is responsible for a task, its current
 * status, and an estimated effort in hours. The annotation is retained at runtime
 * and can be inspected via reflection.</p>
 *
 * <p>Retention: RUNTIME — the annotation is available at runtime for reflective
 * processing.</p>
 *
 * <p>Target: METHOD — this annotation may only be applied to methods.</p>
 *
 * Elements:
 * @return assignedTo the name of the person assigned to the task; default "Unassigned"
 * @return status a short status label (for example "TODO", "IN_PROGRESS", "DONE"); default "TODO"
 * @return estimatedHours estimated effort in hours as an integer; default 0
 *
 * <p>Example:</p>
 * <pre>
 * &#64;TaskStatus(assignedTo = "Alice", status = "IN_PROGRESS", estimatedHours = 4)
 * public void implementFeature() { ... }
 * </pre>
 */
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
