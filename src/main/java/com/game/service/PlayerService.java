package com.game.service;

import com.game.dto.PlayerDto;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Сервис для работы с игроками
 */
public interface PlayerService {


  /**
   * Получение списка игроков
   */
  List<PlayerDto> getPlayers();

  /**
   * Получение игрока
   *
   * @param id Идентификатор игрока
   */
  PlayerDto getPlayer(String id);

  /**
   * Добавление игрока
   *
   * @param playerDto Игрок
   */
  PlayerDto savePlayer(PlayerDto playerDto);

  /**
   * Редактирование игрока
   *
   * @param id Идентификатор игрока
   * @param playerDto Игрок
   */
  PlayerDto updatePlayer(String id, PlayerDto playerDto);

  /**
   * Удаление игрока
   *
   * @param id Идентификатор игрока
   */
  void deletePlayer(String id);

  /**
   * Получение количество игроков
   */
  Integer getPlayersCount();
}
