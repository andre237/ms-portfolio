package com.andre.training.core.shared;

import com.andre.training.core.domain.exception.ApplicationStartupException;
import com.andre.training.core.domain.stereotype.Injectable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Injectable
public class SendNotificationUseCaseRegistry {

    private final Map<NotificationType, List<SendNotificationUseCase>> useCasesCache;

    public SendNotificationUseCaseRegistry(List<SendNotificationUseCase> registeredUseCases) {
        this.useCasesCache = registeredUseCases.stream()
                .collect(Collectors.groupingBy(SendNotificationUseCase::getTypeImplementation));

        this.assertSingleImplementationPerType();
    }

    private void assertSingleImplementationPerType() throws ApplicationStartupException {
        for (var entry : useCasesCache.entrySet()) {
            int numOfImplementations = entry.getValue().size();
            if (numOfImplementations > 1) {
                 String cause = "Found %d implementations for type %s";
                 throw new ApplicationStartupException(String.format(cause, numOfImplementations, entry.getKey().name()));
             }
        }
    }

    public Optional<SendNotificationUseCase> findUseCaseFor(NotificationType type) {
        if (!useCasesCache.containsKey(type)) return Optional.empty();
        return Optional.ofNullable(useCasesCache.get(type).get(0));
    }

}
