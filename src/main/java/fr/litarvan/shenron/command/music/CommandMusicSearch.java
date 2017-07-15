/*
 * Copyright 2016-2017 Adrien 'Litarvan' Navratil
 *
 * This file is part of Shenron.
 *
 * Shenron is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Shenron is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Shenron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.litarvan.shenron.command.music;

import org.krobot.command.CommandContext;
import org.krobot.command.CommandHandler;
import org.krobot.command.SuppliedArgument;
import org.krobot.util.Dialog;
import org.krobot.util.Markdown;
import java.util.Map;
import javax.inject.Inject;
import net.dv8tion.jda.core.entities.Message;
import fr.litarvan.shenron.Music;
import fr.litarvan.shenron.MusicPlayer;
import org.jetbrains.annotations.NotNull;

public class CommandMusicSearch implements CommandHandler
{
    @Inject
    private MusicPlayer player;

    @Override
    public void handle(@NotNull CommandContext context, @NotNull Map<String, SuppliedArgument> args) throws Exception
    {
        Message message = context.sendMessage(Dialog.info("Recherche", "Recherche en cours...")).get();

        Music[] musics = player.search(String.join(" ", args.get("query").getAsStringList()));
        StringBuilder string = new StringBuilder();

        for (Music music : musics)
        {
            string.append("-> ").append(Markdown.bold(music.getName())).append("\n---> ").append(music.getUrl()).append("\n\n");
        }

        message.delete().queue();
        context.sendMessage(Dialog.info(Markdown.underline("Résultats de la recherche :"), string.toString()));
    }
}
