package com.game.dto;

import com.game.entity.Profession;
import com.game.entity.Race;
import java.util.Date;

public class PlayerDto {

  /**
   * Идентификатор игрока
   */
  private Long id;

  /**
   * Имя персонажа
   */
  private String name;

  /**
   * Титул персонажа
   */
  private String title;

  /**
   * Расса персонажа
   */
  private Race race;

  /**
   * Профессия персонажа
   */
  private Profession profession;

  /**
   * Дата регистрации
   */
  private Date birthday;

  /**
   * Забанен / не забанен
   */
  private Boolean banned;

  /**
   * Опыт персонажа
   */
  private Integer experience;

  /**
   * Уровень персонажа
   */
  private Integer level;

  /**
   * Остаток опыта до следующего уровня
   */
  private Integer untilNextLevel;

  public PlayerDto() {
  }

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }

  public Profession getProfession() {
    return profession;
  }

  public void setProfession(Profession profession) {
    this.profession = profession;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Boolean getBanned() {
    return banned;
  }

  public void setBanned(Boolean banned) {
    this.banned = banned;
  }

  public Integer getExperience() {
    return experience;
  }

  public void setExperience(Integer experience) {
    this.experience = experience;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getUntilNextLevel() {
    return untilNextLevel;
  }

  public void setUntilNextLevel(Integer untilNextLevel) {
    this.untilNextLevel = untilNextLevel;
  }
}
