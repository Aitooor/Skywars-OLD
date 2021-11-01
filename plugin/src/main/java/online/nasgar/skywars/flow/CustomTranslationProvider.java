package online.nasgar.skywars.flow;

import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.TranslationProvider;
import online.nasgar.skywars.file.FileHandler;
import online.nasgar.skywars.util.Utils;

import javax.inject.Inject;
import javax.inject.Named;


public class CustomTranslationProvider implements TranslationProvider {

    @Inject @Named("config") private FileHandler config;

    @Override
    public String getTranslation(Namespace namespace, String key) {
        switch(key){
            case "sender.only-player":
                return Utils.ct("&cYou cannot use this command in the console.");
            case "sender.unknown":
                return Utils.ct("&cInvalid player");
            case "command.no-permission":
                return Utils.ct(config.getString("plugin.no-permission"));
            case "command.subcommand.invalid":
                return Utils.ct("&cSubcommand invalid.");

        }
        return Utils.ct("&cYou don't have permissions!");
    }
}
