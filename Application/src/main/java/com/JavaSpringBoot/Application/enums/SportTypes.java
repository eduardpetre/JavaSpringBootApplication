package com.JavaSpringBoot.Application.enums;

public enum SportTypes {
    FOOTBALL, HANDBALL, BASKETBALL, VOLLEYBALL;

    static public boolean isSportType(SportTypes sportType) {
        SportTypes[] sportTypes = SportTypes.values();
        for (SportTypes type : sportTypes){
            if (type.equals(sportType))
                return true;
        }

    return false;
    }
}
