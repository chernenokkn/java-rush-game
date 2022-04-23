package com.game.mapper;
import com.game.entity.Race;
import com.game.entity.Profession;
import java.util.Date;

import com.game.dto.PlayerDto;
import com.game.entity.Player;

public class PlayerMapper {

  public static PlayerDto playerToDto(Player player) {
    PlayerDto playerDto = new PlayerDto();
    playerDto.setId(player.getId());
    playerDto.setName(player.getName());
    playerDto.setTitle(player.getTitle());
    playerDto.setRace(player.getRace());
    playerDto.setProfession(player.getProfession());
    playerDto.setBirthday(player.getBirthday());
    playerDto.setBanned(player.getBanned());
    playerDto.setExperience(player.getExperience());
    playerDto.setLevel(player.getLevel());
    playerDto.setUntilNextLevel(player.getUntilNextLevel());

    return playerDto;
  }

  public static Player dtoToPlayer(PlayerDto playerDto) {
    Player player = new Player();
    player.setId(playerDto.getId());
    player.setName(playerDto.getName());
    player.setTitle(playerDto.getTitle());
    player.setRace(playerDto.getRace());
    player.setProfession(playerDto.getProfession());
    player.setExperience(playerDto.getExperience());
    player.setLevel(playerDto.getLevel());
    player.setUntilNextLevel(playerDto.getUntilNextLevel());
    player.setBirthday(playerDto.getBirthday());
    player.setBanned(playerDto.getBanned());

    return player;
  }
}
