package me.bruce.bhcf.factions;

import lombok.Data;
import me.bruce.bhcf.factions.interfaces.FactionPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Data
public class Faction {

    private UUID uuid;
    private String name;

    private Player leader;

    private List<FactionPlayer> members;

    public Faction(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
