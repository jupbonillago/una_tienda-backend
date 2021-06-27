package com.ingesoft2.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

  @Id
  @SequenceGenerator(name = "QUESTION_QUESTIONID_GENERATOR", sequenceName = "public.question_question_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "QUESTION_QUESTIONID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "question_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private PersonDTO personAsk;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post postAsked;

  @Column(name = "text")
  private String text;

  @OneToMany(mappedBy = "question")
  private List<Answer> answers;


  public Question() {
  }

  public Question(Integer id, PersonDTO personAsk, Post postAsked, String text, List<Answer> answers) {
    this.id = id;
    this.personAsk = personAsk;
    this.postAsked = postAsked;
    this.text = text;
    this.answers = answers;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PersonDTO getPersonAsk() {
    return this.personAsk;
  }

  public void setPersonAsk(PersonDTO personAsk) {
    this.personAsk = personAsk;
  }

  public Post getPostAsked() {
    return this.postAsked;
  }

  public void setPostAsked(Post postAsked) {
    this.postAsked = postAsked;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<Answer> getAnswers() {
    return this.answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public Question id(Integer id) {
    this.id = id;
    return this;
  }

  public Question personAsk(PersonDTO personAsk) {
    this.personAsk = personAsk;
    return this;
  }

  public Question postAsked(Post postAsked) {
    this.postAsked = postAsked;
    return this;
  }

  public Question text(String text) {
    this.text = text;
    return this;
  }

  public Question answers(List<Answer> answers) {
    this.answers = answers;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(personAsk, question.personAsk) && Objects.equals(postAsked, question.postAsked) && Objects.equals(text, question.text) && Objects.equals(answers, question.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personAsk, postAsked, text, answers);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", personAsk='" + getPersonAsk() + "'" +
      ", postAsked='" + getPostAsked() + "'" +
      ", text='" + getText() + "'" +
      ", answers='" + getAnswers() + "'" +
      "}";
  }

}
