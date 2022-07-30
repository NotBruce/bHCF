package me.bruce.bhcf.utils.commands;

import me.bruce.bhcf.utils.commands.annotations.BCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandExecutor extends BukkitCommand {

    public Class<?> command;
    public HashMap<String, Class<?>> subCommands;

    public CommandExecutor(Class<?> command, HashMap<String, Class<?>> subCommands) {
        super(command.getAnnotation(BCommand.class).name());
        this.command = command;
        this.subCommands = subCommands;
        this.description = command.getAnnotation(BCommand.class).description();
        this.setPermission(command.getAnnotation(BCommand.class).permission());
        this.setAliases(new ArrayList<>(Arrays.asList(command.getAnnotation(BCommand.class).aliases())));
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        try {
            if (args.length != 0 && this.subCommands.containsKey(args[0].toLowerCase())) {
                this.subCommands.get(args[0]).getDeclaredMethod("execute", Player.class, String[].class)
                        .invoke( this.subCommands.get(args[0]).newInstance(), (Player) commandSender, args);
                return false;
            }
            else if (args.length != 0) {
                commandSender.sendMessage(ChatColor.RED + "Invalid Sub Command");
                return false;
            }

            // Executing Help Command if exist
            if (command.getAnnotation(BCommand.class).help()) {
                command.getAnnotation(BCommand.class)
                        .helpCommand()
                        .getDeclaredMethod("execute", Player.class, String[].class)
                        .invoke(command.getAnnotation(BCommand.class).helpCommand().newInstance(), (Player) commandSender, args);
                return false;
            }

            command.getDeclaredMethod("execute", Player.class, String[].class)
                    .invoke(command.newInstance(), (Player) commandSender, args);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
