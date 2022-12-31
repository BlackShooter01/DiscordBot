package me.blackshooter01;

import java.lang.String;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.json.JSONObject;


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
            phoenixServer.upsertCommand("zobaczego","Opis wskazanego EGO")
                    .addOption(OptionType.STRING,"nazwa","nazwa EGO",true).queue();
            phoenixServer.upsertCommand("listaego","Lista posiadanych EGO").queue();
            phoenixServer.upsertCommand("zobaczability","Opis wskazanej umiejętności")
                    .addOption(OptionType.STRING,"nazwa","nazwa umiejętności",true).queue();
            phoenixServer.upsertCommand("listaability","Lista posiadanych umiejętności").queue();
            phoenixServer.upsertCommand("tablicaego","tablica EGO z przyciskami").queue();
            phoenixServer.upsertCommand("createchannel", "Stworzenie kanału startowego")
                    .addOption(OptionType.MENTIONABLE,"member","Nowy gracz").queue();
            phoenixServer.upsertCommand("statystyki","Sprawdzenie swoich statystyk")
                    .addOption(OptionType.MENTIONABLE, "member", "Osoba").queue();
            phoenixServer.upsertCommand("rozdaj","Rozdaj statystyki")
                    .addOption(OptionType.STRING,"statystyka","Statystyka", true,true)
                    .addOption(OptionType.INTEGER, "wartość", "Ilość przeznaczanych punktów", true).queue();
            phoenixServer.upsertCommand("nadaj","Bezwarunkowe przydzielanie statystyk")
                    .addOption(OptionType.MENTIONABLE, "member", "Postać",true)
                    .addOption(OptionType.INTEGER,"fortitude","-",false)
                    .addOption(OptionType.INTEGER,"krzepa","-",false)
                    .addOption(OptionType.INTEGER,"odporność","-",false)
                    .addOption(OptionType.INTEGER,"refleks","-",false)
                    .addOption(OptionType.INTEGER,"prudence","-",false)
                    .addOption(OptionType.INTEGER,"wiedza","-",false)
                    .addOption(OptionType.INTEGER,"przystosowanie","-",false)
                    .addOption(OptionType.INTEGER,"intuicja","-",false)
                    .addOption(OptionType.INTEGER,"temperance","-",false)
                    .addOption(OptionType.INTEGER,"unikalna","-",false)
                    .addOption(OptionType.INTEGER,"opanowanie","-",false)
                    .addOption(OptionType.INTEGER,"integrity","-",false)
                    .addOption(OptionType.INTEGER,"justice","-",false)
                    .addOption(OptionType.INTEGER,"bohaterstwo","-",false)
                    .addOption(OptionType.INTEGER,"hojność","-",false)
                    .addOption(OptionType.INTEGER,"serdeczność","-",false).queue();
            phoenixServer.upsertCommand("dusza", "Komenda do szybkiego poświęcania integralności duszy")
                    .addOption(OptionType.MENTIONABLE,"member","Komu poświęcić duszę?",true)
                    .addOption(OptionType.INTEGER,"liczba","Ile tej duszy poświęcić? Uwaga: Ujemna liczba zwiększy integralność!",true)
                    .addOption(OptionType.STRING,"opis","Dodatkowe informacje, jakie się chce zawrzeć.",false).queue();

        }

    }
}
