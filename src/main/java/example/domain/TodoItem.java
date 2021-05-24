package example.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "todo")
@EntityListeners(AuditingEntityListener.class)
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "details", nullable = true)
    private String details;

    @Column(name = "done", nullable = false)
    private boolean done;

    public TodoItem() {
    }

    public TodoItem(String description, String details, boolean done) {
        this.description = description;
        this.details = details;
        this.done = done;
    }

    //region getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    //endregion

}
