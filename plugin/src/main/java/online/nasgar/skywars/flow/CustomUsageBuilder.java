package online.nasgar.skywars.flow;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.command.Command;
import me.fixeddev.commandflow.usage.UsageBuilder;
import net.kyori.text.Component;
import net.kyori.text.TextComponent;
import online.nasgar.skywars.util.Utils;

public class CustomUsageBuilder implements UsageBuilder {

    @Override
    public Component getUsage(CommandContext commandContext) {
        Command toExecute = commandContext.getCommand();
        String label = String.join(" ", commandContext.getLabels());
        Component prefixComponent = TextComponent.of(Utils.ct("&cUsage: &f/"));
        Component labelComponent = TextComponent.of(label);
        Component partComponents = toExecute.getPart().getLineRepresentation();
        if (partComponents != null) {
            labelComponent = prefixComponent.append(labelComponent.append(TextComponent.of(" ")).append(partComponents));
        }
        return labelComponent;
    }
}
