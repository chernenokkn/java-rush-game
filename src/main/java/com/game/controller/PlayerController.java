package com.game.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.game.dto.PlayerDto;
import com.game.service.PlayerService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public List<PlayerDto> getPlayers() {
    return playerService.getPlayers();
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
  public Integer getPlayersCount() {
    return playerService.getPlayersCount();
  }
}
