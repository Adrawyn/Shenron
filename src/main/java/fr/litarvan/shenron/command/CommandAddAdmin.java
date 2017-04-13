package fr.litarvan.shenron.command;

import fr.litarvan.krobot.command.CommandContext;
import fr.litarvan.krobot.command.CommandHandler;
import fr.litarvan.krobot.command.SuppliedArgument;
import fr.litarvan.krobot.config.ConfigProvider;
import java.util.Map;
import javax.inject.Inject;
import net.dv8tion.jda.core.entities.User;
import org.jetbrains.annotations.NotNull;

public class CommandAddAdmin implements CommandHandler
{
    @Inject
    private ConfigProvider config;

    @Override
    public void handle(@NotNull CommandContext context, @NotNull Map<String, SuppliedArgument> args)
    {
        for (User user : args.get("targets").getAsUserList())
        {
            config.get("admins").append(args.get("scope").getAsString(), String[].class, user.getId());
            context.sendMessage("=> Ajouté");
        }
    }
}
