package com.JavaSpringBoot.Application.patterns;

public class DateOfBirth {
    // 01-Jan-1970  -  31-Dec-2099
    public static final String DATE_OF_BIRTH = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\-(19[7-9][0-9]|20[0-9][0-9])$";
}
