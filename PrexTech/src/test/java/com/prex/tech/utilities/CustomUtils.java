package com.prex.tech.utilities;

import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Add custom functions related to your project here
 */

@NoArgsConstructor
public class CustomUtils {

    public List<String> rewardScreenTextVerify(){
        return List.of(
                "Rewards/Create",
                "Reward Info",
                "Reward Details",
                "Info",
                "Mechanics",
                "Review"
        );
    }

    public String validationErrMsg() {
        return "Rewards must have a name.";
    }

    public String dateValidationErrMsg() {
        return "Start date & end date required";
    }
}
