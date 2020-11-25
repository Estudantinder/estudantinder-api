package org.estudantinder.features.Student.CreateStudent.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PreferencesDTO {
    
    @Min(message = "You must be in a school year between 1 - 3", value = 0)
    @Max(message = "You must be in a school year between 1 - 3", value = 3)
    // int is not nullable
    // 0 is used in that case
    public int schoolYear;
    public String schoolShift;
    public String gender;

    public Long courseId;
}
