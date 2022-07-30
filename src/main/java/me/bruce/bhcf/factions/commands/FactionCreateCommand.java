package me.bruce.bhcf.factions.commands;

import me.bruce.bhcf.HCF;
import me.bruce.bhcf.utils.commands.annotations.BSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


@BSubCommand(main = FactionCommand.class, name = "create")
public class FactionCreateCommand {

    public void execute(Player player, String[] args) {
        if (HCF.getINSTANCE().getFactionManager().getPlayerFaction(player) != null) {
            player.sendMessage(ChatColor.RED + "You're already in a faction...");
            return;
        }

        // Create Faction
        if (args.length <= 1) {
            player.sendMessage(ChatColor.RED + "/team create <name>");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "You created the faction " + args[1]);
        HCF.getINSTANCE().getFactionManager().createFaction(player, args[1]);
    }
}
