package me.bruce.bhcf.factions;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.*;

public class FactionManager {

    @Getter private final List<Faction> factions = new ArrayList<>();
    private final Map<UUID, Faction> uuidPlayerFactionMap = new HashMap<>();

    public FactionManager() {
        this.reloadFactionData();
    }

    public void reloadFactionData() {
        // MongoDB Load Faction Data
    }


    public void createFaction(Player player, String name) {
        Faction faction = new Faction(UUID.randomUUID(), name);
        faction.setLeader(player);
        factions.add(faction);
        uuidPlayerFactionMap.put(player.getUniqueId(), faction);
    }

    public Faction getPlayerFaction(UUID uuid) {
        return uuidPlayerFactionMap.get(uuid);
    }

    public Faction getPlayerFaction(Player player) {
        return uuidPlayerFactionMap.get(player.getUniqueId());
    }
}
