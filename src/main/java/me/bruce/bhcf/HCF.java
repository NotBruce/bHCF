package me.bruce.bhcf;

import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import me.bruce.bhcf.factions.Faction;
import me.bruce.bhcf.factions.FactionManager;
import me.bruce.bhcf.listeners.ChatListener;
import me.bruce.bhcf.scoreboard.ScoreboardProvider;
import me.bruce.bhcf.utils.commands.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HCF extends JavaPlugin {

    @Getter private static HCF INSTANCE;

    @Getter private CommandHandler commandHandler;

    @Getter private FactionManager factionManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Assemble assemble = new Assemble(this, new ScoreboardProvider());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.VIPER);

        commandHandler = new CommandHandler(true);
        factionManager = new FactionManager();

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }
}
