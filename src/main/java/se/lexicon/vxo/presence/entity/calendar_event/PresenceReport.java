package se.lexicon.vxo.presence.entity.calendar_event;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;
import se.lexicon.vxo.presence.entity.user.AppUser;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PresenceReport {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String reportId;
    private Presence presenceStatus;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "date", referencedColumnName = "date")
    private AppCalendarDay day;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private AppUser user;

    public PresenceReport(Presence presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public PresenceReport(){}

    public String getReportId() {
        return reportId;
    }

    public Presence getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(Presence presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public AppCalendarDay getDay() {
        return day;
    }

    public void setDay(AppCalendarDay day) {
        this.day = day;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresenceReport that = (PresenceReport) o;
        return Objects.equals(reportId, that.reportId) &&
                presenceStatus == that.presenceStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, presenceStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PresenceReport{");
        sb.append("reportId='").append(reportId).append('\'');
        sb.append(", presenceStatus=").append(presenceStatus);
        sb.append('}');
        return sb.toString();
    }
}
