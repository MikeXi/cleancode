package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exceptions.TechnicalErrorException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        try {
            String totalMessage = getUserTotalMessage(userId);
            validateTotalMessage(totalMessage);
            model.addAttribute("userTotalMessage", totalMessage);
        }catch (Exception e){
            return e.getMessage();
        }
        return "userTotal";
    }

    private void validateTotalMessage(String totalMessage) {
        if (totalMessage == null)
            throw new TechnicalErrorException();
    }

    private String getUserTotalMessage(String userId) {
        Double amount = null;
        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        }catch (Exception e){
            return e.getMessage();
        }

        return "User Total: " + amount + "$";
    }


    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
