package me.blackshooter01;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.util.ArrayList;

public class BotCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equals("ping")){
            event.reply("pong!").queue();
        }
        else if (event.getName().equals("send")) {
            OptionMapping option = event.getOption("message");
            if(option == null)
            {
                event.reply("Bład Vortixa.").queue();
            }
            else{
                event.deferReply().queue();
                event.getHook().sendMessage(option.getAsString()).queue();
            }
        }
        else if(event.getName().equals("database_save")) {
            event.deferReply().queue();
            DB db = DBMaker.fileDB(config.fileDB).make();
            HTreeMap myMap = db.hashMap("myMap").createOrOpen();
            myMap.put(event.getMember().getIdLong(),event.getOption("message").getAsString());
            db.close();
            event.getHook().sendMessage("Wiadomość zapisana!").queue();
        }
        else if(event.getName().equals("database_load")) {
            event.deferReply().queue();
            DB db = DBMaker.fileDB(config.fileDB).make();
            HTreeMap myMap = db.hashMap("myMap").open();
            String key = (String) myMap.get(event.getMember().getIdLong());
            event.getHook().sendMessage("Twoja wiadomość to: "+key).queue();
            db.close();
        }
        else if(event.getName().equals("anomalia")) {
            event.deferReply().queue();
            Abnormality abnormality = new Item();
            MessageEmbed eb = ItemEmbbed.WeaponEmbbed(abnormality);
            event.getHook().sendMessageEmbeds(eb).queue();
        }
        else if (event.getName().equals("zobaczego"))
        {
            event.deferReply().setEphemeral(true).queue();
            OptionMapping option = event.getOption("nazwa");
            Parser parser = new Parser();
            Abnormality abnormality;
            try
            {
                System.out.println("Komenda ZobaczEGO użyta przez: "+event.getMember().getUser()+" Indeks EGO: "+option.getAsInt());
                abnormality = parser.Parser(event.getMember().getIdLong(),option.getAsInt()-1);

            }
            catch(NumberFormatException e)
            {
                System.out.println("To nie jest liczba! Próbuję uznać to za nazwę!");
                System.out.println("Komenda ZobaczEGO użyta przez: "+event.getMember().getUser()+" Nazwa EGO: "+option.getAsString());
                abnormality = parser.Parser(event.getMember().getIdLong(),option.getAsString());
            }
            if (abnormality == null ){ event.getHook().sendMessage("Nie istnieje/posiadasz danego EGO.").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.WeaponEmbbed(abnormality)).queue(); }

        }
        else if (event.getName().equals("listaego"))
        {
            event.deferReply().setEphemeral(true).queue();
            System.out.println("Komenda ListaEGO użyta przez: "+event.getMember().getUser());
            Parser parser = new Parser ();
            ArrayList<Abnormality> abnormality = parser.Parser(event.getMember().getIdLong());
            if(abnormality==null) { event.getHook().sendMessage("```Brak EGO!```").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.ListaEGOEmbbed(abnormality)).queue(); }
        }
    }
}
