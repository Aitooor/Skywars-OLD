package online.nasgar.skywars.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import org.bukkit.command.CommandSender;

@Command(
        names = {"sw", "skywars"},
        permission = "sw.cmds"
)
public class SWCommand implements CommandClass {



    @Command(
            names = ""
    ) public void onMainCommand(CommandSender sender){

    }
}
