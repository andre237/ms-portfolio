package com.andre.training.core.email.application.usecases;

import com.andre.training.core.application.UseCase;

import static com.andre.training.core.email.application.usecases.FindEmailTemplateUseCase.*;

public class FindEmailTemplateUseCase extends UseCase<EmailtTemplateInput, EmailTemplateOutput> {

    @Override
    public EmailTemplateOutput execute(EmailtTemplateInput input) {
        return null;
    }

    public record EmailtTemplateInput(String emailType) implements UseCase.Input {}

    public static class EmailTemplateOutput implements UseCase.Output {

    }

}
