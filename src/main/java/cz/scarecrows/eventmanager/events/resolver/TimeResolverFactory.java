package cz.scarecrows.eventmanager.events.resolver;

import cz.scarecrows.eventmanager.events.EventType;
import cz.scarecrows.eventmanager.events.resolver.impl.DefaultTimeResolver;
import cz.scarecrows.eventmanager.events.resolver.impl.MatchTimeResolver;
import cz.scarecrows.eventmanager.events.resolver.impl.TrainingTimeResolver;

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
