package com.game.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.game.dto.PlayerDto;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import com.game.util.PlayerSpecification;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для игрока
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/rest/players")
public class PlayerController {

  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  /**
   * Получение списка игроков
   */
  @GetMapping()
  public List<PlayerDto> getPlayers(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) Race race,
      @RequestParam(required = false) Profession profession,
      @RequestParam(required = false) Long after,
      @RequestParam(required = false) Long before,
      @RequestParam(required = false) Boolean banned,
      @RequestParam(required = false) Double minExperience,
      @RequestParam(required = false) Double maxExperience,
      @RequestParam(required = false) Double maxLevel,
      @RequestParam(required = false) Double minLevel,
      @RequestParam(required = false) PlayerOrder order,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "3") int pageSize) {

    Specification<Player> spec = Specification.where(PlayerSpecification.nameFilter(name))
        .and(PlayerSpecification.titleFilter(title))
        .and(PlayerSpecification.bannedFilter(banned))
        .and(PlayerSpecification.levelFilter(minLevel, maxLevel))
        .and(PlayerSpecification.dateFilter(after, before))
        .and(PlayerSpecification.professionFilter(profession))
        .and(PlayerSpecification.raceFilter(race))
        .and(PlayerSpecification.experienceFilter(minExperience, maxExperience))
        .and(PlayerSpecification.order(order));

    return playerService.getPlayers(spec, PageRequest.of(pageNumber, pageSize));
  }

  /**
   * Получение игрока
   *
   * @param id Идентификатор игрока
   */
  @GetMapping(path = "{id}")
  public PlayerDto getPlayer(@PathVariable String id) {
    return playerService.getPlayer(id);
  }

  /**
   * Добавление игрока
   *
   * @param playerDto Игрок
   */
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public PlayerDto savePlayer(@RequestBody PlayerDto playerDto) {
    return playerService.savePlayer(playerDto);
  }

  /**
   * Редактирование игрока
   *
   * @param id        Идентификатор игрока
   * @param playerDto Игрок
   */
  @PostMapping(path = "{id}")
  public PlayerDto savePlayer(@PathVariable String id, @RequestBody PlayerDto playerDto) {
    return playerService.updatePlayer(id, playerDto);
  }

  /**
   * Удаление игрока
   *
   * @param id Идентификатор игрока
   */
  @DeleteMapping(path = "{id}")
  public void deletePlayer(@PathVariable String id) {
    playerService.deletePlayer(id);
  }

  /**
   * Получение количество игроков
   */
  @GetMapping(path = "count")
  public Integer getPlayersCount(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) Race race,
      @RequestParam(required = false) Profession profession,
      @RequestParam(required = false) Long after,
      @RequestParam(required = false) Long before,
      @RequestParam(required = false) Boolean banned,
      @RequestParam(required = false) Double minExperience,
      @RequestParam(required = false) Double maxExperience,
      @RequestParam(required = false) Double maxLevel,
      @RequestParam(required = false) Double minLevel) {

    Specification<Player> spec = Specification.where(PlayerSpecification.nameFilter(name))
        .and(PlayerSpecification.titleFilter(title))
        .and(PlayerSpecification.bannedFilter(banned))
        .and(PlayerSpecification.levelFilter(minLevel, maxLevel))
        .and(PlayerSpecification.dateFilter(after, before))
        .and(PlayerSpecification.professionFilter(profession))
        .and(PlayerSpecification.raceFilter(race))
        .and(PlayerSpecification.experienceFilter(minExperience, maxExperience));

    return playerService.getPlayersCount(spec);
  }
}
