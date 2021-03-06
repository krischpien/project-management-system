package cz.vsmie.krist.pms.logging.dto;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LoggingEventException generated by hbm2java
 */
@Entity
@Table(name = "logging_event_exception", catalog = "pms")
public class LoggingEventException implements java.io.Serializable {

    @Id @GeneratedValue
    @Column(name = "event_id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, insertable = false, updatable = false)
    private LoggingEvent loggingEvent;
    @Column(name = "trace_line", nullable = false, length = 254)
    private String traceLine;



    public LoggingEvent getLoggingEvent() {
        return this.loggingEvent;
    }

    public void setLoggingEvent(LoggingEvent loggingEvent) {
        this.loggingEvent = loggingEvent;
    }

    public String getTraceLine() {
        return this.traceLine;
    }

    public void setTraceLine(String traceLine) {
        this.traceLine = traceLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}