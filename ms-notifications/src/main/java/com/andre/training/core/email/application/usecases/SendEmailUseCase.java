package com.andre.training.core.email.application.usecases;

import com.andre.training.core.domain.stereotype.Injectable;
import com.andre.training.core.email.domain.port.EmailSender;
import com.andre.training.core.shared.NotificationType;
import com.andre.training.core.shared.SendNotificationUseCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j @Injectable
public class SendEmailUseCase extends SendNotificationUseCase {

    private final EmailSender emailSender;
    private final FindEmailTemplateUseCase findEmailTemplateUseCase;
    private final ResolveEmailContextUseCase resolveEmailContextUseCase;

    public SendEmailUseCase(EmailSender emailSender,
                            FindEmailTemplateUseCase findEmailTemplateUseCase,
                            ResolveEmailContextUseCase resolveEmailContextUseCase) {
        this.emailSender = emailSender;
        this.findEmailTemplateUseCase = findEmailTemplateUseCase;
        this.resolveEmailContextUseCase = resolveEmailContextUseCase;
    }

    @Override
    public VoidOutput execute(NotificationInput input) {
        var template = findEmailTemplateUseCase.execute(null);
        var emailBody = resolveEmailContextUseCase.execute(null); // template goes here

        this.emailSender.sendEmail("template obj contains subject", "emailBody as string", input.recipients());

        return VoidOutput.create();
    }

    @Override
    public NotificationType getTypeImplementation() {
        return NotificationType.EMAIL;
    }
}
