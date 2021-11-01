package online.nasgar.skywars.util;

import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public interface Utils {

    static void log(String... args){
        for(String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + str));
    }

    static void logError(String... args){
        for(String str : args){
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + "[ERROR] &c" + str));
        }
    }

    static String ct(String source){
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    static void send(User user, String prefix, String... args){
        Player player = user.getPlayer();
        if(player == null){
            return;
        }
        send(player, prefix, args);
    }

    static void send(CommandSender sender, String prefix, String... args) {
        for (String arg : args) {
            sender.sendMessage(ct(prefix + arg));
        }
    }

    static String getPrefix(){
        SkyWars skyWars = SkyWars.getPlugin(SkyWars.class);
        return  "[" + skyWars.getName() + "] ";
    }

    static void loadFiles(SkyWars skyWars, String... files){
        for(String name : files) {
            if (skyWars.getResource(name) != null) {
                skyWars.saveResource(name, false);
            }
        }
    }
}
