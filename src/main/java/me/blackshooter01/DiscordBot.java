package me.blackshooter01;

import java.lang.String;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.json.JSONObject;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;


public class DiscordBot
{
    public static void main(String[] args) throws InterruptedException {
        //JSONfiles
        JSONObject obj = new JSONObject();

        //DiscordBot
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
            phoenixServer.upsertCommand("anomalia", "Opis przykładowej, losowej broni EGO.").queue();
            //phoenixServer.upsertCommand("check","Test").queue();
            phoenixServer.upsertCommand("checkone","test2")
                    .addOption(OptionType.STRING,"nazwa","nazwa EGO",true).queue();
            phoenixServer.upsertCommand("listaego","Lista nazw Twoich EGO").queue();
        }

    }
}
