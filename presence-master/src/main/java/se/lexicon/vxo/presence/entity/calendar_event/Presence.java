package se.lexicon.vxo.presence.entity.calendar_event;

public enum Presence {
    NV("Närvarande"),
    FV("Frånvarande"),
    SJ("Sjuk"),
    JH("Jobbar hemma"),
    BLANK("Ej angivet");

    private String status;

    Presence(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
