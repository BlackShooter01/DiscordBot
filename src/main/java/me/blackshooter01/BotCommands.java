package me.blackshooter01;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import net.dv8tion.jda.internal.interactions.component.ButtonInteractionImpl;
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
            event.getHook().sendMessageEmbeds(eb).addActionRow(Button.secondary("anomalia_reset", "Reset")).queue();
        }
        else if (event.getName().equals("zobaczego"))
        {
            event.deferReply().setEphemeral(true).queue();
            OptionMapping option = event.getOption("nazwa");
            Parser parser = new Parser();
            Abnormality abnormality;
            try
            {
                System.out.println("Komenda ZobaczEGO użyta przez "+event.getMember().getUser()+" Indeks EGO: "+option.getAsInt());
                abnormality = parser.Parser(event.getMember().getIdLong(),option.getAsInt()-1);

            }
            catch(NumberFormatException e)
            {
                System.out.println("To nie jest liczba! Próbuję uznać to za nazwę!");
                System.out.println("Komenda ZobaczEGO użyta przez "+event.getMember().getUser()+" Nazwa EGO: "+option.getAsString());
                abnormality = parser.Parser(event.getMember().getIdLong(),option.getAsString());
            }
            if (abnormality == null ){ event.getHook().sendMessage("Nie istnieje/posiadasz danego EGO.").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.WeaponEmbbed(abnormality)).queue(); }

        }
        else if (event.getName().equals("listaego"))
        {
            event.deferReply().setEphemeral(true).queue();
            System.out.println("Komenda ListaEGO użyta przez "+event.getMember().getUser());
            Parser parser = new Parser ();
            ArrayList<Abnormality> abnormality = parser.Parser(event.getMember().getIdLong());
            if(abnormality==null) { event.getHook().sendMessage("```Brak EGO!```").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.ListaEGOEmbbed(abnormality)).queue(); }
        }
        else if (event.getName().equals("tablicaego"))
        {
            event.deferReply(true).queue();
            System.out.println("Komenda ListaEGO użyta przez "+event.getMember().getUser());
            Parser parser = new Parser ();
            Abnormality abnormality=null;
            try
            {
                abnormality = parser.Parser(event.getMember().getIdLong(),0);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            if(abnormality!=null)
            {
                event.getHook().sendMessageEmbeds(ItemEmbbed.WeaponEmbbed(abnormality))
                        .addActionRow(
                                Button.secondary("tablica_Left", "<<"),
                                Button.secondary("tablica_indeks","1").asDisabled(),
                                Button.secondary("tablica_Right", ">>")
                        ).queue();
            }
            else
            {
                event.getHook().sendMessage("Coś poszło nie tak! Brak EGO?!").queue();
            }

        }
    }
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event)
    {
        if(event.getInteraction().getButton().isDisabled())
        {
            event.getChannel().sendMessage("Ten przycisk podobno nie działa").queue();
        }
        if(event.getComponentId().equals("tablica_Left"))
        {
            event.deferEdit().queue();
            int indeks = Integer.parseInt(event.getMessage().getButtons().get(1).getLabel())-1;
            System.out.println("W lewo!");
            System.out.println(indeks);

            Parser parser = new Parser();
            Abnormality abnormality = null;
            try
            {
                abnormality = parser.Parser(event.getMember().getIdLong(),indeks-1);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            if(abnormality!=null)
            {
                event.getHook().editOriginalEmbeds(ItemEmbbed.WeaponEmbbed(abnormality))
                        .setActionRow(
                                Button.secondary("tablica_Left", "<<"),
                                Button.secondary("tablica_indeks", String.valueOf(indeks)).asDisabled(),
                                Button.secondary("tablica_Right", ">>")
                        ).queue();
            }
            else
            {
                event.getHook().editOriginalEmbeds(ItemEmbbed.FailEGO())
                        .setActionRow(
                                Button.secondary("tablica_Left", "<<"),
                                Button.secondary("tablica_indeks", String.valueOf(indeks)).asDisabled(),
                                Button.secondary("tablica_Right", ">>")
                        ).queue();
            }
        }
        else if(event.getComponentId().equals("tablica_Right"))
        {
            event.deferEdit().queue();
            int indeks = Integer.parseInt(event.getMessage().getButtons().get(1).getLabel())+1;
            System.out.println("W prawo!");
            System.out.println(indeks);

            Parser parser = new Parser();
            Abnormality abnormality = null;
            try{
                abnormality = parser.Parser(event.getMember().getIdLong(),indeks-1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if(abnormality!=null)
            {
                event.getHook().editOriginalEmbeds(ItemEmbbed.WeaponEmbbed(abnormality))
                        .setActionRow(
                                Button.secondary("tablica_Left", "<<"),
                                Button.secondary("tablica_indeks", String.valueOf(indeks)).asDisabled(),
                                Button.secondary("tablica_Right", ">>")
                        ).queue();
            }
            else
            {
                event.getHook().editOriginalEmbeds(ItemEmbbed.FailEGO())
                        .setActionRow(
                                Button.secondary("tablica_Left", "<<"),
                                Button.secondary("tablica_indeks", String.valueOf(indeks)).asDisabled(),
                                Button.secondary("tablica_Right", ">>")
                        ).queue();
            }
        }
        else if(event.getComponentId().equals("anomalia_reset"))
        {
            event.deferEdit().queue();
            Abnormality abnormality = new Item();
            MessageEmbed eb = ItemEmbbed.WeaponEmbbed(abnormality);
            event.getHook().editOriginalEmbeds(eb).queue();
        }

    }
}
