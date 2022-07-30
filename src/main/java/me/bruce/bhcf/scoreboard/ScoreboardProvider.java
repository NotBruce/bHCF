package me.bruce.bhcf.scoreboard;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.bruce.bhcf.HCF;
import me.bruce.bhcf.factions.Faction;
import me.bruce.bhcf.utils.BukkitUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardProvider implements AssembleAdapter {
    @Override
    public String getTitle(Player player) {
        return ChatColor.GOLD + ChatColor.BOLD.toString() + "Factions" + ChatColor.WHITE + " ⎜ v1.0.0";
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();

        if (HCF.getINSTANCE().getFactionManager().getPlayerFaction(player) != null) {
            Faction faction = HCF.getINSTANCE().getFactionManager().getPlayerFaction(player);
            lines.add(ChatColor.YELLOW + "Name: " + ChatColor.BLUE + faction.getName());
        }

        if (!lines.isEmpty()) {
            lines.add(0, ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
            lines.add(lines.size(), ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        }


        return lines;
    }
}
