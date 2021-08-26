package com.envyful.reforged.gts.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.type.UtilParse;
import com.envyful.reforged.gts.forge.ReforgedGTSForge;
import com.envyful.reforged.gts.forge.ui.SelectTypeUI;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Objects;

@Command(
        value = "sell",
        description = "For selling items to the GTS",
        aliases = {
                "s"
        }
)
@Permissible("reforged.gts.command.sell")
public class SellCommand {

    @CommandProcessor
    public void onSellCommand(@Sender EntityPlayerMP player, String[] args) {
        ForgeEnvyPlayer sender = ReforgedGTSForge.getInstance().getPlayerManager().getPlayer(player);

        if (args.length == 0) {
            SelectTypeUI.openUI(sender);
            return;
        }

        if (args.length != 2) {
            sender.message(UtilChatColour.translateColourCodes('&',
                    "&c&l(!) &cInsufficient args! /gts s <amount> <price>"));
            return;
        }

        ItemStack inHand = player.getHeldItemMainhand();

        if (Objects.equals(inHand.getItem(), Items.AIR)) {
            sender.message(UtilChatColour.translateColourCodes('&',
                    "&c&l(!) &cYou must have an item in your hand to sell!"));
            return;
        }

        int amount = UtilParse.parseInteger(args[0]).orElse(-1);

        if (amount <= 0) {
            sender.message(UtilChatColour.translateColourCodes('&',
                    "&c&l(!) &cAmount must be a positive number!"));
            return;
        }

        double price = UtilParse.parseDouble(args[1]).orElse(-1.0);

        if (price < 1.0) {
            sender.message(UtilChatColour.translateColourCodes('&',
                    "&c&l(!) &cPrice cannot be less than $1"));
            return;
        }

        //TODO: create trade and add to GTS
    }
}
