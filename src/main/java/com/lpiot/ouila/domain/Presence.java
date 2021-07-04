package com.lpiot.ouila.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    private User student;

    @ManyToOne(optional = false, targetEntity = Lesson.class)
    @JoinColumn(name = "lesson_id", nullable = false, referencedColumnName = "id")
    private Lesson lesson;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Justification.class)
    @JoinColumn(name = "justification_id", referencedColumnName = "id")
    private Justification justification;

    @Column(nullable = false)
    private Boolean status;

}
