package online.nasgar.skywars.message.provider;

import me.yushust.message.MessageHandler;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.message.user.liguist.UserLinguist;
import online.nasgar.skywars.message.user.sender.UserMessageSender;
import online.nasgar.skywars.util.Utils;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.File;

public class MessageHandlerProvider implements Provider<MessageHandler> {

    @Inject private SkyWars skyWars;

    @Override
    public MessageHandler get() {
        MessageSourceDecorator messageSourceDecorator =
                MessageSourceDecorator.decorate(
                        BukkitMessageAdapt.newYamlSource(
                                skyWars,
                                new File(
                                        skyWars.getDataFolder(),
                                        "languages"
                                )
                        )
                );
        MessageSource messageSource = messageSourceDecorator
                .addFallbackLanguage("en")
                .addFallbackLanguage("es")
                .get();
        Utils.loadFiles(
                skyWars,
                "languages/lang_en.yml", "languages/lang_es.yml");
        return MessageHandler.of(
            messageSource,
            config -> {
                config.addInterceptor(Utils::ct);

                config.specify(User.class)
                        .setMessageSender(new UserMessageSender())
                        .setLinguist(new UserLinguist());
            }
        );
    }
}
