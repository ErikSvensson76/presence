package se.lexicon.vxo.presence.entity.calendar_event;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;
import se.lexicon.vxo.presence.entity.user.AppUser;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate date;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private AppUser user;

    public PresenceReport(Presence presenceStatus, LocalDate date) {
        setPresenceStatus(presenceStatus);
        setDate(date);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        return "PresenceReport{" +
                "reportId='" + reportId + '\'' +
                ", presenceStatus=" + presenceStatus +
                ", date=" + date +
                '}';
    }
}
