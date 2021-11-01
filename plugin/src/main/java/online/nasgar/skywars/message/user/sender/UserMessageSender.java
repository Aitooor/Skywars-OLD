package online.nasgar.skywars.message.user.sender;

import me.yushust.message.send.MessageSender;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.util.Utils;
import org.bukkit.entity.Player;

public class UserMessageSender implements MessageSender<User> {

    @Override
    public void send(User user, String mode, String message) {
        Utils.send(
                user,
                "",
                message
        );
    }
}
