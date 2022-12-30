package me.blackshooter01;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;

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
            Abnormality abnormality;
            try
            {
                System.out.println("Komenda ZobaczEGO użyta przez "+event.getMember().getUser()+" Indeks EGO: "+option.getAsInt());
                abnormality = Parser.ItemParser(event.getMember().getIdLong(),option.getAsInt()-1);

            }
            catch(NumberFormatException e)
            {
                System.out.println("To nie jest liczba! Próbuję uznać to za nazwę!");
                System.out.println("Komenda ZobaczEGO użyta przez "+event.getMember().getUser()+" Nazwa EGO: "+option.getAsString());
                abnormality = Parser.ItemParser(event.getMember().getIdLong(),option.getAsString());
            }
            if (abnormality == null ){ event.getHook().sendMessageEmbeds(ItemEmbbed.FailEGO()).queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.WeaponEmbbed(abnormality)).queue(); }
        }

        else if (event.getName().equals("listaego"))
        {
            event.deferReply().setEphemeral(true).queue();
            System.out.println("Komenda ListaEGO użyta przez "+event.getMember().getUser());
            ArrayList<Abnormality> abnormality = Parser.ItemParser(event.getMember().getIdLong());
            if(abnormality==null) { event.getHook().sendMessage("```Brak EGO!```").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.ListaEGOEmbbed(abnormality)).queue(); }
        }

        else if (event.getName().equals("tablicaego"))
        {
            event.deferReply(true).queue();
            System.out.println("Komenda ListaEGO użyta przez "+event.getMember().getUser());
            Abnormality abnormality=null;
            try
            {
                abnormality = Parser.ItemParser(event.getMember().getIdLong(),0);
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

        else if (event.getName().equals("zobaczability"))
        {
            event.deferReply().setEphemeral(true).queue();
            OptionMapping option = event.getOption("nazwa");
            Abnormality abnormality;
            try
            {
                System.out.println("Komenda ZobaczAbility użyta przez "+event.getMember().getUser()+" Indeks Ability: "+option.getAsInt());
                abnormality = Parser.AbilityParser(event.getMember().getIdLong(),option.getAsInt()-1);

            }
            catch(NumberFormatException e)
            {
                System.out.println("To nie jest liczba! Próbuję uznać to za nazwę!");
                System.out.println("Komenda ZobaczAbility użyta przez "+event.getMember().getUser()+" Nazwa Ability: "+option.getAsString());
                abnormality = Parser.AbilityParser(event.getMember().getIdLong(),option.getAsString());
            }
            if (abnormality == null ){ event.getHook().sendMessage("Nie istnieje/posiadasz danej umiejętności.").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.WeaponEmbbed(abnormality)).queue(); }
        }

        else if (event.getName().equals("listaability"))
        {
            event.deferReply().setEphemeral(true).queue();
            System.out.println("Komenda ListaAbility użyta przez "+event.getMember().getUser());
            ArrayList<Abnormality> abnormality = Parser.AbilityParser(event.getMember().getIdLong());
            if(abnormality==null) { event.getHook().sendMessage("```Brak Umiejętności!```").queue(); }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.ListaEGOEmbbed(abnormality)).queue(); }
        }

        else if (event.getName().equals("createchannel"))
        {
            event.deferReply(false).queue();
            if(!event.getMember().hasPermission(Permission.MANAGE_CHANNEL))
            {
                event.reply("Brak wystarczających uprawnień!").queue();
            }
            else {
                try
                {
                    OptionMapping option = event.getOption("member");
                    event.getGuild().getCategoryById("947501728654311424").createTextChannel("zajęta-tożsamość")
                            .addPermissionOverride(option.getAsMember(), EnumSet.of(Permission.VIEW_CHANNEL,Permission.MESSAGE_SEND,Permission.MESSAGE_ADD_REACTION,Permission.MESSAGE_EMBED_LINKS,Permission.MESSAGE_ATTACH_FILES,Permission.CREATE_PRIVATE_THREADS,Permission.CREATE_PUBLIC_THREADS,Permission.MESSAGE_SEND_IN_THREADS), null)
                            .addPermissionOverride(event.getGuild().getRoleById("953261314996863037"), EnumSet.of(Permission.VIEW_CHANNEL), null)
                            .removePermissionOverride(event.getGuild().getRoleById("944593596328317019"))
                            .addPermissionOverride(event.getGuild().getRoleById("844235271335313428"),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete().sendMessageEmbeds(ItemEmbbed.WelcomeChannelMessage()).queue();
                    event.getHook().sendMessage("Kanał został stworzony!").queue();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    event.getHook().sendMessage("Wystąpił nieznany błąd!").queue();
                }
            }
        }

        else if (event.getName().equals("statystyki"))
        {
            event.deferReply(true).queue();
            OptionMapping option = event.getOption("member");
            System.out.print("Komenda Statystyki użyta przez: "+event.getMember().getUser());
            if(option!=null) { System.out.println(" Z dodatkowym parametrem: "+option.getAsMember().getUser());} else { System.out.println();}
            if(option!=null)
            {
                if(event.getMember().hasPermission(Permission.MANAGE_SERVER))
                {
                    event.getHook().sendMessageEmbeds(ItemEmbbed.StatsEmbbed((Stats) Parser.StatsParser(option.getAsMember().getIdLong()))).queue();
                }
                else
                {
                    event.getHook().sendMessage("Brak wystarczających uprawnień!").queue();
                }
            }
            else { event.getHook().sendMessageEmbeds(ItemEmbbed.StatsEmbbed((Stats) Parser.StatsParser(event.getMember().getIdLong()))).queue();
            }
        }

        else if (event.getName().equals("rozdaj"))
        {
            event.deferReply(true).queue();
            String option = event.getOption("statystyka").getAsString();
            int var = event.getOption("wartość").getAsInt();
            Stats statystyki = (Stats) Parser.StatsParser(event.getMember().getIdLong());
            long memberId = event.getMember().getIdLong();
            switch (option) {
                case "Krzepa", "Odporność", "Refleks" -> StatsManager.Przydziel(statystyki.getFortitude(),var,option,memberId);
                case "Wiedza", "Przystosowanie", "Intuicja" -> StatsManager.Przydziel(statystyki.getPrudence(),var,option,memberId);
                case "Unikalna", "Opanowanie", "Integralność duszy" -> StatsManager.Przydziel(statystyki.getTemperance(),var,option,memberId);
                case "Bohaterstwo", "Hojność", "Serdeczność" -> StatsManager.Przydziel(statystyki.getJustice(),var,option,memberId);
                default -> event.getHook().sendMessage("Nieprawidłowy wybór").queue();
            }
            event.getHook().sendMessage("Done!").queue();
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

            Abnormality abnormality = null;
            try
            {
                abnormality = Parser.ItemParser(event.getMember().getIdLong(),indeks-1);
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

            Abnormality abnormality = null;
            try{
                abnormality = Parser.ItemParser(event.getMember().getIdLong(),indeks-1);
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
    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent event)
    {
        if(event.getFullCommandName().equals("rozdaj"))
        {
            String[] choice = new String[] {"Krzepa","Odporność","Refleks","Wiedza","Przystosowanie","Intuicja","Unikalna","Opanowanie","Integralność duszy","Bohaterstwo","Hojność","Serdeczność"};
            event.replyChoiceStrings(choice).queue();
        }
    }
}
