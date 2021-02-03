package org.estudantinder.features.Users.EditUserFilters;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class DTO {
    
    @Min(message = "You must be in a school year between 1 - 3", value = 0)
    @Max(message = "You must be in a school year between 1 - 3", value = 3)
    // int is not nullable
    // 0 is used in that case
    public int school_year;
    
    @Max(message = "Shift must be between 1(morning) and 2(evening)", value = 2)
    public int shift;
    public String gender;

    public Long course_id;

    @Size(message = "Your number of subjects must be between 1 and 3", max = 3)
    public List<Long> subjects_ids;
}
