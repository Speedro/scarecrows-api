package cz.scarecrows.eventmanager.exception;

public class MatchStartInLessThenTwoHoursException extends IllegalStateException {

    public MatchStartInLessThenTwoHoursException(final String s) {
        super(s);
    }
}
