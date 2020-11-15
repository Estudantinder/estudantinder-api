package org.estudantinder.features.Student.CreateStudent.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PreferencesDTO {
    
    @Min(message = "You must be in a school year between 1 - 3", value = 1)
    @Max(message = "You must be in a school year between 1 - 3", value = 3)
    public int schoolYear;
    public String schoolShift;
    public String gender;

    public Long courseId;
}
