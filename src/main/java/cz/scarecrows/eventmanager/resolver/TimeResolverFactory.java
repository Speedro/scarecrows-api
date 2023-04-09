package cz.scarecrows.eventmanager.resolver;

import cz.scarecrows.eventmanager.data.EventType;
import cz.scarecrows.eventmanager.resolver.impl.DefaultTimeResolver;
import cz.scarecrows.eventmanager.resolver.impl.MatchTimeResolver;
import cz.scarecrows.eventmanager.resolver.impl.TrainingTimeResolver;

public class TimeResolverFactory {

    public static EventTimeResolver getResolver(final EventType eventType) {
        switch (eventType) {
            case MATCH:
                return new MatchTimeResolver();
            case TRAINING:
                return new TrainingTimeResolver();
            default:
                return new DefaultTimeResolver();
        }
    }
}
