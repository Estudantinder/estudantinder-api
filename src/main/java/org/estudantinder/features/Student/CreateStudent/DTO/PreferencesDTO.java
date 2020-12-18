package org.estudantinder.features.Student.CreateStudent.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PreferencesDTO {
    
    @Min(message = "You must be in a school year between 1 - 3", value = 0)
    @Max(message = "You must be in a school year between 1 - 3", value = 3)
    // int is not nullable
    // 0 is used in that case
    public int school_year;
    
    @Min(message = "Shift must be between 1(morning) and 2(evening)", value = 0)
    @Max(message = "Shift must be between 1(morning) and 2(evening)", value = 2)
    public int shift;
    public String gender;

    public Long course_id;
}
