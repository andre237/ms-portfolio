package com.andre.training.core.email.application.usecases;

import com.andre.training.core.application.UseCase;
import com.andre.training.core.domain.stereotype.Injectable;

import static com.andre.training.core.email.application.usecases.FindEmailTemplateUseCase.*;

@Injectable
public class FindEmailTemplateUseCase extends UseCase<EmailtTemplateInput, EmailTemplateOutput> {

    @Override
    public EmailTemplateOutput execute(EmailtTemplateInput input) {
        return null;
    }

    public record EmailtTemplateInput(String emailType) implements UseCase.Input {}

    public static class EmailTemplateOutput implements UseCase.Output {

    }

}
