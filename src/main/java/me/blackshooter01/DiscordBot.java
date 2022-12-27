package me.blackshooter01;

import java.lang.String;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;


public class DiscordBot
{
    public static void main(String[] args) throws InterruptedException {
        //config.database();
        //Discord bot
        JDA jda = JDABuilder.createDefault(config.getToken()).addEventListeners(new BotCommands()).build().awaitReady();

        Guild testServer = jda.getGuildById("598632356005543955");

        if(testServer != null)
        {
            testServer.upsertCommand("ping","Ping pong!").queue();
            testServer.upsertCommand("send","Sending.")
                    .addOption(OptionType.STRING,"message", "content", true).queue();
        }

        Guild phoenixServer = jda.getGuildById("844235271335313428");

        if(phoenixServer != null)
        {
            phoenixServer.upsertCommand("ping","Ping pong!").queue();
            phoenixServer.upsertCommand("database_save","zapisanie swojej wiadomości")
                    .addOption(OptionType.STRING,"message","content",true).queue();
            phoenixServer.upsertCommand("database_load","odczytanie swojej wiadomości").queue();
        }

    }
}
