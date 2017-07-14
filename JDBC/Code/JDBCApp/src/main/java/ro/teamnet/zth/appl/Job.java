package ro.teamnet.zth.appl;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Cosmin.Adamut on 7/12/2017.
 */
@Table(name = "jobs")
public class Job {
    @Id(name = "JOB_ID")
    private Long id;

    @Column(name = "JOB_TITLE")
    private String title;

    @Column(name = "MIN_SALARY")
    private Long minSalary;

    @Column(name = "MAX_SALARY")
    private Long maxSalary;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (id != job.id) return false;
        if (minSalary != job.minSalary) return false;
        if (maxSalary != job.maxSalary) return false;
        return title != null ? title.equals(job.title) : job.title == null;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }

}
