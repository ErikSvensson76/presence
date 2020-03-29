package se.lexicon.vxo.presence.entity;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Presence{");
        sb.append("status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
