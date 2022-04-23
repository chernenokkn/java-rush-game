package com.game.service.impl;

import com.game.dto.PlayerDto;
import com.game.mapper.PlayerMapper;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public List<PlayerDto> getPlayers() {
    return playerRepository.findAll(PageRequest.of(0, 10)).get().map(PlayerMapper::playerToDto).collect(Collectors.toList());
  }

  @Override
  public PlayerDto getPlayer(String id) {
    return playerRepository.findById(id).map(PlayerMapper::playerToDto).orElse(null);
  }

  @Override
  public PlayerDto savePlayer(PlayerDto playerDto) {
    return null;
  }

  @Override
  public PlayerDto updatePlayer(String id, PlayerDto playerDto) {
    return null;
  }

  @Override
  public void deletePlayer(String id) {

  }

  @Override
  public Integer getPlayersCount() {
    return null;
  }
}
