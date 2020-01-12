package com.github.pawelsosulski.skillscollector.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToMany
    @JoinTable(name = "sources_attached_skills", joinColumns = @JoinColumn(name = "source_id")
            ,inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> attachedSkills;


    @ManyToMany(mappedBy = "knownSource")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Skill> getAttachedSkills() {
        return attachedSkills;
    }



    public void setAttachedSkills(List<Skill> attachedSkills) {
        this.attachedSkills = attachedSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;

        Source source = (Source) o;

        if (!Objects.equals(id, source.id)) return false;
        if (!Objects.equals(name, source.name)) return false;
        return Objects.equals(description, source.description);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id,name,description);
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
