package me.bruce.bhcf.utils.commands;

import lombok.Data;
import me.bruce.bhcf.utils.commands.annotations.BCommand;
import me.bruce.bhcf.utils.commands.annotations.BSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class CommandHandler {

    private Map<String, Map<String, Class<?>>> commands = new HashMap<>();
    public CommandHandler(boolean autoRegister) {
        if (autoRegister) {
            Reflections reflections = new Reflections("me.bruce.bhcf");
            Set<Class<?>> commandsClasses = reflections.getTypesAnnotatedWith(BCommand.class);
            commandsClasses.forEach(object -> {
                System.out.println("[bHCF] Registering " + object.getAnnotation(BCommand.class).name());
                HashMap<String, Class<?>> subCommands = reflections.getTypesAnnotatedWith(BSubCommand.class)
                        .stream()
                        .collect(Collectors
                                .toMap(subCommand -> subCommand.getAnnotation(BSubCommand.class).name(),
                                        subCommand -> subCommand, (a, b) -> b, HashMap::new));
                System.out.println("[bHCF] " + object.getAnnotation(BCommand.class).name() + " Sub Commands Registered Successfully");

                // In Case it's needed for anything ig?
                commands.put(object.getAnnotation(BCommand.class).name(), subCommands);
                try {
                    this.registerCommand(object, subCommands);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void registerCommand(Class<?> object, HashMap<String, Class<?>> subCommands) throws IllegalAccessException, NoSuchFieldException {
        final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        BCommand command = object.getAnnotation(BCommand.class);
        commandMap.register(command.name(), new CommandExecutor(object, subCommands));
    }

}
